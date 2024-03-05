package kt.mq.loop

import kt.mq.loop.Message.Companion.obtain
import java.io.FileDescriptor
import kotlin.math.max
import kotlin.math.min

class MessageQueue internal constructor(// True if the message queue can be quit.
    private val mQuitAllowed: Boolean
) {

    private var mMessages: Message? = null

    private var mQuitting = false

    // Indicates whether next() is blocked waiting in pollOnce() with a non-zero timeout.
    private var mBlocked = false

    // The next barrier token.
    // Barriers are indicated by messages with a null target whose arg1 field carries the token.
    private var mNextBarrierToken = 0

    @Throws(Throwable::class)
    protected fun finalize() {
        try {
            dispose()
        } finally {
        }
    }

    // Disposes of the underlying message queue.
    // Must only be called on the looper thread or the finalizer.
    private fun dispose() {
    }

    val isIdle: Boolean
        /**
         * Returns true if the looper has no pending messages which are due to be processed.
         *
         *
         * This method is safe to call from any thread.
         *
         * @return True if the looper is idle.
         */
        get() {
            synchronized(this) {
                val now = System.currentTimeMillis()
                return mMessages == null || now < mMessages!!.time
            }
        }


    val isPolling: Boolean
        /**
         * Returns whether this looper's thread is currently polling for more work to do.
         * This is a good signal that the loop is still alive rather than being stuck
         * handling a callback.  Note that this method is intrinsically racy, since the
         * state of the loop can change before you get the result back.
         *
         *
         * This method is safe to call from any thread.
         *
         * @return True if the looper is currently polling for events.
         * @hide
         */
        get() {
            synchronized(this) {
                return isPollingLocked
            }
        }

    private val isPollingLocked: Boolean
        get() =// If the loop is quitting then it must not be idling.
            !mQuitting



    fun next(): Message? {

        var nextPollTimeoutMillis = 0
        while (true) {

            synchronized(this) {
                // Try to retrieve the next message.  Return if found.
                val now = System.currentTimeMillis()
                var prevMsg: Message? = null
                var msg = mMessages
                if (msg != null && msg.target == null) {
                    // Stalled by a barrier.  Find the next asynchronous message in the queue.
                    do {
                        prevMsg = msg
                        msg = msg!!.next
                    } while (msg != null && !msg.isAsynchronous)
                }
                if (msg != null) {
                    if (now < msg.time) {
                        // Next message is not ready.  Set a timeout to wake up when it is ready.
                        nextPollTimeoutMillis = min((msg.time - now).toInt(), Int.MAX_VALUE) as Int
                    } else {
                        // Got a message.
                        mBlocked = false
                        if (prevMsg != null) {
                            prevMsg.next = msg.next
                        } else {
                            mMessages = msg.next
                        }
                        msg.next = null
                        msg.markInUse()
                        return msg
                    }
                } else {
                    // No more messages.
                    nextPollTimeoutMillis = -1
                }

                // Process the quit message now that all pending messages have been handled.
                if (mQuitting) {
                    dispose()
                    return null
                }


            }

            // While calling an idle handler, a new message could have been delivered
            // so go back and look again for a pending message without waiting.
            nextPollTimeoutMillis = 0
        }
    }

    fun quit(safe: Boolean) {
        check(mQuitAllowed) { "Main thread not allowed to quit." }

        synchronized(this) {
            if (mQuitting) {
                return
            }
            mQuitting = true

            if (safe) {
                removeAllFutureMessagesLocked()
            } else {
                removeAllMessagesLocked()
            }
        }
    }

    /**
     * Posts a synchronization barrier to the Looper's message queue.
     *
     * Message processing occurs as usual until the message queue encounters the
     * synchronization barrier that has been posted.  When the barrier is encountered,
     * later synchronous messages in the queue are stalled (prevented from being executed)
     * until the barrier is released by calling [.removeSyncBarrier] and specifying
     * the token that identifies the synchronization barrier.
     *
     * This method is used to immediately postpone execution of all subsequently posted
     * synchronous messages until a condition is met that releases the barrier.
     * Asynchronous messages (see [Message.isAsynchronous] are exempt from the barrier
     * and continue to be processed as usual.
     *
     * This call must be always matched by a call to [.removeSyncBarrier] with
     * the same token to ensure that the message queue resumes normal operation.
     * Otherwise the application will probably hang!
     *
     * @return A token that uniquely identifies the barrier.  This token must be
     * passed to [.removeSyncBarrier] to release the barrier.
     *
     * @hide
     */
    fun postSyncBarrier(): Int {
        return postSyncBarrier(System.currentTimeMillis())
    }

    private fun postSyncBarrier(time: Long): Int {
        // Enqueue a new sync barrier token.
        // We don't need to wake the queue because the purpose of a barrier is to stall it.
        synchronized(this) {
            val token = mNextBarrierToken++
            val msg = obtain()
            msg!!.markInUse()
            msg.time = time
            msg.arg1 = token

            var prev: Message? = null
            var p = mMessages
            if (time != 0L) {
                while (p != null && p.time <= time) {
                    prev = p
                    p = p.next
                }
            }
            if (prev != null) { // invariant: p == prev.next
                msg.next = p
                prev.next = msg
            } else {
                msg.next = p
                mMessages = msg
            }
            return token
        }
    }

    /**
     * Removes a synchronization barrier.
     *
     * @param token The synchronization barrier token that was returned by
     * [.postSyncBarrier].
     *
     * @throws IllegalStateException if the barrier was not found.
     *
     * @hide
     */
    fun removeSyncBarrier(token: Int) {
        // Remove a sync barrier token from the queue.
        // If the queue is no longer stalled by a barrier then wake it.
        synchronized(this) {
            var prev: Message? = null
            var p = mMessages
            while (p != null && (p.target != null || p.arg1 != token)) {
                prev = p
                p = p.next
            }
            checkNotNull(p) {
                ("The specified message queue synchronization "
                        + " barrier token has not been posted or has already been removed.")
            }
            val needWake: Boolean
            if (prev != null) {
                prev.next = p.next
                needWake = false
            } else {
                mMessages = p.next
                needWake = mMessages == null || mMessages!!.target != null
            }
            p.recycleUnchecked()

        }
    }

    fun enqueueMessage(msg: Message, time: Long): Boolean {
        requireNotNull(msg.target) { "Message must have a target." }

        synchronized(this) {
            check(!msg.isInUse) { "$msg This message is already in use." }
            if (mQuitting) {
                val e = IllegalStateException(
                    msg.target.toString() + " sending message to a Handler on a dead thread"
                )
                msg.recycle()
                return false
            }

            msg.markInUse()
            msg.time = time
            var p = mMessages
            var needWake: Boolean
            if (p == null || time == 0L || time < p.time) {
                // New head, wake up the event queue if blocked.
                msg.next = p
                mMessages = msg
                needWake = mBlocked
            } else {
                // Inserted within the middle of the queue.  Usually we don't have to wake
                // up the event queue unless there is a barrier at the head of the queue
                // and the message is the earliest asynchronous message in the queue.
                needWake = mBlocked && p.target == null && msg.isAsynchronous
                var prev: Message?
                while (true) {
                    prev = p
                    p = p!!.next
                    if (p == null || time < p.time) {
                        break
                    }
                    if (needWake && p.isAsynchronous) {
                        needWake = false
                    }
                }
                msg.next = p // invariant: p == prev.next
                prev!!.next = msg
            }

        }
        return true
    }

    fun hasMessages(h: Handler?, what: Int, `object`: Any?): Boolean {
        if (h == null) {
            return false
        }

        synchronized(this) {
            var p = mMessages
            while (p != null) {
                if (p.target === h && p.what == what && (`object` == null || p.obj === `object`)) {
                    return true
                }
                p = p.next
            }
            return false
        }
    }

    fun hasEqualMessages(h: Handler?, what: Int, `object`: Any?): Boolean {
        if (h == null) {
            return false
        }

        synchronized(this) {
            var p = mMessages
            while (p != null) {
                if (p.target === h && p.what == what && (`object` == null || `object` == p.obj)) {
                    return true
                }
                p = p.next
            }
            return false
        }
    }

    fun hasMessages(h: Handler?, r: Runnable, `object`: Any?): Boolean {
        if (h == null) {
            return false
        }

        synchronized(this) {
            var p = mMessages
            while (p != null) {
                if (p.target === h && p.callback === r && (`object` == null || p.obj === `object`)) {
                    return true
                }
                p = p.next
            }
            return false
        }
    }

    fun hasMessages(h: Handler?): Boolean {
        if (h == null) {
            return false
        }

        synchronized(this) {
            var p = mMessages
            while (p != null) {
                if (p.target === h) {
                    return true
                }
                p = p.next
            }
            return false
        }
    }

    fun removeMessages(h: Handler?, what: Int, `object`: Any?) {
        if (h == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && p.what == what && (`object` == null || p.obj === `object`)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && n.what == what && (`object` == null || n.obj === `object`)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }

    fun removeEqualMessages(h: Handler?, what: Int, `object`: Any?) {
        if (h == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && p.what == what && (`object` == null || `object` == p.obj)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && n.what == what && (`object` == null || `object` == n.obj)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }

    fun removeMessages(h: Handler?, r: Runnable?, `object`: Any?) {
        if (h == null || r == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && p.callback === r && (`object` == null || p.obj === `object`)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && n.callback === r && (`object` == null || n.obj === `object`)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }

    fun removeEqualMessages(h: Handler?, r: Runnable?, `object`: Any?) {
        if (h == null || r == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && p.callback === r && (`object` == null || `object` == p.obj)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && n.callback === r && (`object` == null || `object` == n.obj)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }


    fun removeCallbacksAndMessages(h: Handler?, `object`: Any?) {
        if (h == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && (`object` == null || p.obj === `object`)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && (`object` == null || n.obj === `object`)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }

    fun removeCallbacksAndEqualMessages(h: Handler?, `object`: Any?) {
        if (h == null) {
            return
        }

        synchronized(this) {
            var p = mMessages
            // Remove all messages at front.
            while (p != null && p.target === h && (`object` == null || `object` == p.obj)) {
                val n = p.next
                mMessages = n
                p.recycleUnchecked()
                p = n
            }

            // Remove all messages after front.
            while (p != null) {
                val n = p.next
                if (n != null) {
                    if (n.target === h && (`object` == null || `object` == n.obj)) {
                        val nn = n.next
                        n.recycleUnchecked()
                        p.next = nn
                        continue
                    }
                }
                p = n
            }
        }
    }

    private fun removeAllMessagesLocked() {
        var p = mMessages
        while (p != null) {
            val n = p.next
            p.recycleUnchecked()
            p = n
        }
        mMessages = null
    }

    private fun removeAllFutureMessagesLocked() {
        val now = System.currentTimeMillis()
        var p = mMessages
        if (p != null) {
            if (p.time > now) {
                removeAllMessagesLocked()
            } else {
                var n: Message?
                while (true) {
                    n = p!!.next
                    if (n == null) {
                        return
                    }
                    if (n.time > now) {
                        break
                    }
                    p = n
                }
                p!!.next = null
                do {
                    p = n
                    n = p!!.next
                    p.recycleUnchecked()
                } while (n != null)
            }
        }
    }

    /**
     * Callback interface for discovering when a thread is going to block
     * waiting for more messages.
     */
    interface IdleHandler {
        /**
         * Called when the message queue has run out of messages and will now
         * wait for more.  Return true to keep your idle handler active, false
         * to have it removed.  This may be called if there are still messages
         * pending in the queue, but they are all scheduled to be dispatched
         * after the current time.
         */
        fun queueIdle(): Boolean
    }
}