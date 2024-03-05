package kt.mq.loop

class Looper private constructor(quitAllowed: Boolean) {
    /**
     * Gets this looper's message queue.
     *
     * @return The looper's message queue.
     */
    val queue: MessageQueue = MessageQueue(quitAllowed)

    /**
     * Gets the Thread associated with this Looper.
     *
     * @return The looper's thread.
     */
    val thread: Thread = Thread.currentThread()

    private var mInLoop = false

    private val mTraceTag: Long = 0

    /**
     * If set, the looper will show a warning log if a message dispatch takes longer than this.
     */
    private var mSlowDispatchThresholdMs: Long = 0

    /**
     * If set, the looper will show a warning log if a message delivery (actual delivery time -
     * post time) takes longer than this.
     */
    private var mSlowDeliveryThresholdMs: Long = 0

    /**
     * True if a message delivery takes longer than [.mSlowDeliveryThresholdMs].
     */
    private var mSlowDeliveryDetected = false

    val isCurrentThread: Boolean
        /**
         * Returns true if the current thread is this looper's thread.
         */
        get() = Thread.currentThread() === thread

    /**
     * Set a thresholds for slow dispatch/delivery log.
     * {@hide}
     */
    fun setSlowLogThresholdMs(slowDispatchThresholdMs: Long, slowDeliveryThresholdMs: Long) {
        mSlowDispatchThresholdMs = slowDispatchThresholdMs
        mSlowDeliveryThresholdMs = slowDeliveryThresholdMs
    }

    /**
     * Quits the looper.
     *
     *
     * Causes the [.loop] method to terminate without processing any
     * more messages in the message queue.
     *
     *
     * Any attempt to post messages to the queue after the looper is asked to quit will fail.
     * For example, the [Handler.sendMessage] method will return false.
     *
     *
     * Using this method may be unsafe because some messages may not be delivered
     * before the looper terminates.  Consider using [.quitSafely] instead to ensure
     * that all pending work is completed in an orderly manner.
     *
     *
     * @see .quitSafely
     */
    fun quit() {
        queue.quit(false)
    }

    /**
     * Quits the looper safely.
     *
     *
     * Causes the [.loop] method to terminate as soon as all remaining messages
     * in the message queue that are already due to be delivered have been handled.
     * However pending delayed messages with due times in the future will not be
     * delivered before the loop terminates.
     *
     *
     * Any attempt to post messages to the queue after the looper is asked to quit will fail.
     * For example, the [Handler.sendMessage] method will return false.
     *
     */
    fun quitSafely() {
        queue.quit(true)
    }

    override fun toString(): String {
        return ("Looper (" + thread.name + ", tid " + thread.id
                + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}")
    }

    /** {@hide}  */
    interface Observer {
        /**
         * Called right before a message is dispatched.
         *
         *
         *  The token type is not specified to allow the implementation to specify its own type.
         *
         * @return a token used for collecting telemetry when dispatching a single message.
         * The token token must be passed back exactly once to either
         * [Observer.messageDispatched] or [Observer.dispatchingThrewException]
         * and must not be reused again.
         */
        fun messageDispatchStarting(): Any?

        /**
         * Called when a message was processed by a Handler.
         *
         * @param token Token obtained by previously calling
         * [Observer.messageDispatchStarting] on the same Observer instance.
         * @param msg The message that was dispatched.
         */
        fun messageDispatched(token: Any?, msg: Message?)

        /**
         * Called when an exception was thrown while processing a message.
         *
         * @param token Token obtained by previously calling
         * [Observer.messageDispatchStarting] on the same Observer instance.
         * @param msg The message that was dispatched and caused an exception.
         * @param exception The exception that was thrown.
         */
        fun dispatchingThrewException(token: Any?, msg: Message?, exception: Exception?)
    }

    companion object {
        /*
         * API Implementation Note:
         *
         * This class contains the code required to set up and manage an event loop
         * based on MessageQueue.  APIs that affect the state of the queue should be
         * defined on MessageQueue or Handler rather than on Looper itself.  For example,
         * idle handlers and sync barriers are defined on the queue whereas preparing the
         * thread, looping, and quitting are defined on the looper.
         */
        private const val TAG = "Looper"

        // sThreadLocal.get() will return null unless you've called prepare().
        val sThreadLocal: ThreadLocal<Looper> = ThreadLocal()
        private var sMainLooper: Looper? = null // guarded by Looper.class
        private var sObserver: Observer? = null

        /** Initialize the current thread as a looper.
         * This gives you a chance to create handlers that then reference
         * this looper, before actually starting the loop. Be sure to call
         * [.loop] after calling this method, and end it by calling
         * [.quit].
         */
        fun prepare() {
            prepare(true)
        }

        private fun prepare(quitAllowed: Boolean) {
            if (sThreadLocal.get() != null) {
                throw RuntimeException("Only one Looper may be created per thread")
            }
            sThreadLocal.set(Looper(quitAllowed))
        }


        val mainLooper: Looper?
            /**
             * Returns the application's main looper, which lives in the main thread of the application.
             */
            get() {
                synchronized(Looper::class.java) {
                    return sMainLooper
                }
            }

        /**
         * Set the transaction observer for all Loopers in this process.
         *
         * @hide
         */
        fun setObserver(observer: Observer?) {
            sObserver = observer
        }

        /**
         * Poll and deliver single message, return true if the outer loop should continue.
         */
        private fun loopOnce(
            me: Looper,
            ident: Long, thresholdOverride: Int
        ): Boolean {
            val msg = me.queue.next()
                ?: // No message indicates that the message queue is quitting.
                return false // might block

            // This must be in a local variable, in case a UI event sets the logger

            // Make sure the observer won't change while processing a transaction.
            val observer = sObserver

            val traceTag = me.mTraceTag
            var slowDispatchThresholdMs = me.mSlowDispatchThresholdMs
            var slowDeliveryThresholdMs = me.mSlowDeliveryThresholdMs
            if (thresholdOverride > 0) {
                slowDispatchThresholdMs = thresholdOverride.toLong()
                slowDeliveryThresholdMs = thresholdOverride.toLong()
            }
            val logSlowDelivery = (slowDeliveryThresholdMs > 0)
            val logSlowDispatch = (slowDispatchThresholdMs > 0)

            val needStartTime = logSlowDelivery || logSlowDispatch
            val needEndTime = logSlowDispatch


            val dispatchStart = if (needStartTime) System.currentTimeMillis() else 0
            val dispatchEnd: Long
            var token: Any? = null
            if (observer != null) {
                token = observer.messageDispatchStarting()
            }
            try {
                msg.target?.dispatchMessage(msg)
                observer?.messageDispatched(token, msg)
                dispatchEnd = if (needEndTime) System.currentTimeMillis() else 0
            } catch (exception: Exception) {
                observer?.dispatchingThrewException(token, msg, exception)
                throw exception
            } finally {
                if (traceTag != 0L) {
                }
            }

            if (logSlowDispatch) {
                showSlowLog(slowDispatchThresholdMs, dispatchStart, dispatchEnd, "dispatch", msg)
            }

            // Make sure that during the course of dispatching the
            // identity of the thread wasn't corrupted.
            msg.recycleUnchecked()

            return true
        }

        /**
         * Run the message queue in this thread. Be sure to call
         * [.quit] to end the loop.
         */
        fun loop() {
            val me = myLooper()
                ?: throw RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.")
            if (me.mInLoop) {
            }

            me.mInLoop = true

            // Make sure the identity of this thread is that of the local process,
            // and keep track of what that identity token actually is.

            // Allow overriding a threshold with a system prop. e.g.
            // adb shell 'setprop log.looper.1000.main.slow 1 && stop && start'
            val thresholdOverride = 0

            me.mSlowDeliveryDetected = false

            while (true) {
                if (!loopOnce(me, 0, thresholdOverride)) {
                    return
                }
            }
        }

        private fun showSlowLog(
            threshold: Long, measureStart: Long, measureEnd: Long,
            what: String, msg: Message
        ): Boolean {
            val actualTime = measureEnd - measureStart
            if (actualTime < threshold) {
                return false
            }
            // For slow delivery, the current message isn't really important, but log it anyway.
            return true
        }

        /**
         * Return the Looper object associated with the current thread.  Returns
         * null if the calling thread is not associated with a Looper.
         */
        fun myLooper(): Looper {
            return sThreadLocal.get()
        }

        /**
         * Return the [MessageQueue] object associated with the current
         * thread.  This must be called from a thread running a Looper, or a
         * NullPointerException will be thrown.
         */
        fun myQueue(): MessageQueue {
            return myLooper()!!.queue
        }
    }
}