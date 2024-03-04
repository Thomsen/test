package kt.thread.loop

import java.lang.reflect.Modifier

open class Handler {
    /**
     * Callback interface you can use when instantiating a Handler to avoid
     * having to implement your own subclass of Handler.
     */
    interface Callback {
        fun handleMessage(msg: Message): Boolean
    }

    /**
     * Subclasses must implement this to receive messages.
     */
    open fun handleMessage(msg: Message) {
    }

    /**
     * Handle system messages here.
     */
    fun dispatchMessage(msg: Message) {
        if (msg.callback != null) {
            handleCallback(msg)
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return
                }
            }
            handleMessage(msg)
        }
    }

    /**
     * Default constructor associates this handler with the [Looper] for the
     * current thread.
     *
     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     *
     */
    @Deprecated(
        """Implicitly choosing a Looper during Handler construction can lead to bugs
        where operations are silently lost (if the Handler is not expecting new tasks and quits),
        crashes (if a handler is sometimes created on a thread without a Looper active), or race
        conditions, where the thread a handler is associated with is not what the author
        anticipated. Instead, use an {@link java.util.concurrent.Executor} or specify the Looper
        explicitly, using {@link Looper#getMainLooper}, {link android.view.View#getHandler}, or
        similar. If the implicit thread local behavior is required for compatibility, use
        {@code new Handler(Looper.myLooper())} to make it clear to readers."""
    )
    constructor() : this(null, false)

    /**
     * Constructor associates this handler with the [Looper] for the
     * current thread and takes a callback interface in which you can handle
     * messages.
     *
     * If this thread does not have a looper, this handler won't be able to receive messages
     * so an exception is thrown.
     *
     * @param callback The callback interface in which to handle messages, or null.
     *
     */
    @Deprecated(
        """Implicitly choosing a Looper during Handler construction can lead to bugs
        where operations are silently lost (if the Handler is not expecting new tasks and quits),
        crashes (if a handler is sometimes created on a thread without a Looper active), or race
        conditions, where the thread a handler is associated with is not what the author
        anticipated. Instead, use an {@link java.util.concurrent.Executor} or specify the Looper
        explicitly, using {@link Looper#getMainLooper}, {link android.view.View#getHandler}, or
        similar. If the implicit thread local behavior is required for compatibility, use
        {@code new Handler(Looper.myLooper(), callback)} to make it clear to readers."""
    )
    constructor(callback: Callback?) : this(callback, false)

    constructor(async: Boolean) : this(null, async)


    constructor(callback: Callback?, async: Boolean) {
        if (FIND_POTENTIAL_LEAKS) {
            val klass: Class<out Handler> = javaClass
            if ((klass.isAnonymousClass || klass.isMemberClass || klass.isLocalClass) &&
                (klass.modifiers and Modifier.STATIC) == 0
            ) {
            }
        }

        looper = Looper.myLooper()
        if (looper == null) {
            throw RuntimeException(
                "Can't create handler inside thread " + Thread.currentThread()
                        + " that has not called Looper.prepare()"
            )
        }
        mQueue = looper.queue
        mCallback = callback
        mAsynchronous = async
    }

    /**
     * Use the provided [Looper] instead of the default one and take a callback
     * interface in which to handle messages.  Also set whether the handler
     * should be asynchronous.
     *
     * Handlers are synchronous by default unless this constructor is used to make
     * one that is strictly asynchronous.
     *
     * Asynchronous messages represent interrupts or events that do not require global ordering
     * with respect to synchronous messages.  Asynchronous messages are not subject to
     * the synchronization barriers introduced by conditions such as display vsync.
     *
     * @param looper The looper, must not be null.
     * @param callback The callback interface in which to handle messages, or null.
     * @param async If true, the handler calls [Message.setAsynchronous] for
     * each [Message] that is sent to it or [Runnable] that is posted to it.
     *
     * @hide
     */
    /**
     * Use the provided [Looper] instead of the default one.
     *
     * @param looper The looper, must not be null.
     */
    /**
     * Use the provided [Looper] instead of the default one and take a callback
     * interface in which to handle messages.
     *
     * @param looper The looper, must not be null.
     * @param callback The callback interface in which to handle messages, or null.
     */
    @JvmOverloads
    constructor(looper: Looper, callback: Callback? = null, async: Boolean = false) {
        this.looper = looper
        mQueue = looper.queue
        mCallback = callback
        mAsynchronous = async
    }

    /** {@hide}  */
    fun getTraceName(message: Message): String {
        val sb = StringBuilder()
        sb.append(javaClass.name).append(": ")
        if (message.callback != null) {
            sb.append(message.callback!!.javaClass.name)
        } else {
            sb.append("#").append(message.what)
        }
        return sb.toString()
    }

    /**
     * Returns a string representing the name of the specified message.
     * The default implementation will either return the class name of the
     * message callback if any, or the hexadecimal representation of the
     * message "what" field.
     *
     * @param message The message whose name is being queried
     */
    fun getMessageName(message: Message): String {
        if (message.callback != null) {
            return message.callback!!.javaClass.name
        }
        return "0x" + Integer.toHexString(message.what)
    }

    fun obtainMessage(): Message {
        return Message.obtain(this)
    }

    /**
     * Same as [.obtainMessage], except that it also sets the what member of the returned Message.
     *
     * @param what Value to assign to the returned Message.what field.
     * @return A Message from the global message pool.
     */
    fun obtainMessage(what: Int): Message {
        return Message.obtain(this, what)
    }

    /**
     *
     * Same as [.obtainMessage], except that it also sets the what and obj members
     * of the returned Message.
     *
     * @param what Value to assign to the returned Message.what field.
     * @param obj Value to assign to the returned Message.obj field.
     * @return A Message from the global message pool.
     */
    fun obtainMessage(what: Int, obj: Any?): Message {
        return Message.obtain(this, what, obj)
    }

    /**
     *
     * Same as [.obtainMessage], except that it also sets the what, arg1 and arg2 members of the returned
     * Message.
     * @param what Value to assign to the returned Message.what field.
     * @param arg1 Value to assign to the returned Message.arg1 field.
     * @param arg2 Value to assign to the returned Message.arg2 field.
     * @return A Message from the global message pool.
     */
    fun obtainMessage(what: Int, arg1: Int, arg2: Int): Message {
        return Message.obtain(this, what, arg1, arg2)
    }

    /**
     *
     * Same as [.obtainMessage], except that it also sets the what, obj, arg1,and arg2 values on the
     * returned Message.
     * @param what Value to assign to the returned Message.what field.
     * @param arg1 Value to assign to the returned Message.arg1 field.
     * @param arg2 Value to assign to the returned Message.arg2 field.
     * @param obj Value to assign to the returned Message.obj field.
     * @return A Message from the global message pool.
     */
    fun obtainMessage(what: Int, arg1: Int, arg2: Int, obj: Any?): Message {
        return Message.obtain(this, what, arg1, arg2, obj)
    }

    /**
     * Causes the Runnable r to be added to the message queue.
     * The runnable will be run on the thread to which this handler is
     * attached.
     *
     * @param r The Runnable that will be executed.
     *
     * @return Returns true if the Runnable was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    fun post(r: Runnable): Boolean {
        return sendMessageDelayed(getPostMessage(r), 0)
    }

    fun postAtTime(r: Runnable, uptimeMillis: Long): Boolean {
        return sendMessageAtTime(getPostMessage(r), uptimeMillis)
    }

    fun postAtTime(
        r: Runnable, token: Any?, uptimeMillis: Long
    ): Boolean {
        return sendMessageAtTime(getPostMessage(r, token), uptimeMillis)
    }

    fun postDelayed(r: Runnable, delayMillis: Long): Boolean {
        return sendMessageDelayed(getPostMessage(r), delayMillis)
    }

    /** @hide
     */
    fun postDelayed(r: Runnable, what: Int, delayMillis: Long): Boolean {
        return sendMessageDelayed(getPostMessage(r).setWhat(what), delayMillis)
    }

    fun postDelayed(
        r: Runnable, token: Any?, delayMillis: Long
    ): Boolean {
        return sendMessageDelayed(getPostMessage(r, token), delayMillis)
    }

    /**
     * Posts a message to an object that implements Runnable.
     * Causes the Runnable r to executed on the next iteration through the
     * message queue. The runnable will be run on the thread to which this
     * handler is attached.
     * **This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.**
     *
     * @param r The Runnable that will be executed.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    fun postAtFrontOfQueue(r: Runnable): Boolean {
        return sendMessageAtFrontOfQueue(getPostMessage(r))
    }

    /**
     * Runs the specified task synchronously.
     *
     *
     * If the current thread is the same as the handler thread, then the runnable
     * runs immediately without being enqueued.  Otherwise, posts the runnable
     * to the handler and waits for it to complete before returning.
     *
     *
     * This method is dangerous!  Improper use can result in deadlocks.
     * Never call this method while any locks are held or use it in a
     * possibly re-entrant manner.
     *
     *
     * This method is occasionally useful in situations where a background thread
     * must synchronously await completion of a task that must run on the
     * handler's thread.  However, this problem is often a symptom of bad design.
     * Consider improving the design (if possible) before resorting to this method.
     *
     *
     * One example of where you might want to use this method is when you just
     * set up a Handler thread and need to perform some initialization steps on
     * it before continuing execution.
     *
     *
     * If timeout occurs then this method returns `false` but the runnable
     * will remain posted on the handler and may already be in progress or
     * complete at a later time.
     *
     *
     * When using this method, be sure to use [Looper.quitSafely] when
     * quitting the looper.  Otherwise [.runWithScissors] may hang indefinitely.
     * (TODO: We should fix this by making MessageQueue aware of blocking runnables.)
     *
     *
     * @param r The Runnable that will be executed synchronously.
     * @param timeout The timeout in milliseconds, or 0 to wait indefinitely.
     *
     * @return Returns true if the Runnable was successfully executed.
     * Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     *
     * @hide This method is prone to abuse and should probably not be in the API.
     * If we ever do make it part of the API, we might want to rename it to something
     * less funny like runUnsafe().
     */
    fun runWithScissors(r: Runnable, timeout: Long): Boolean {
        requireNotNull(r) { "runnable must not be null" }
        require(timeout >= 0) { "timeout must be non-negative" }

        if (Looper.myLooper() == looper) {
            r.run()
            return true
        }

        val br = BlockingRunnable(r)
        return br.postAndWait(this, timeout)
    }

    /**
     * Remove any pending posts of Runnable r that are in the message queue.
     */
    fun removeCallbacks(r: Runnable) {
        mQueue.removeMessages(this, r, null)
    }

    /**
     * Remove any pending posts of Runnable <var>r</var> with Object
     * <var>token</var> that are in the message queue.  If <var>token</var> is null,
     * all callbacks will be removed.
     */
    fun removeCallbacks(r: Runnable, token: Any?) {
        mQueue.removeMessages(this, r, token)
    }

    /**
     * Pushes a message onto the end of the message queue after all pending messages
     * before the current time. It will be received in [.handleMessage],
     * in the thread attached to this handler.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    fun sendMessage(msg: Message): Boolean {
        return sendMessageDelayed(msg, 0)
    }

    /**
     * Sends a Message containing only the what value.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    fun sendEmptyMessage(what: Int): Boolean {
        return sendEmptyMessageDelayed(what, 0)
    }

    fun sendEmptyMessageDelayed(what: Int, delayMillis: Long): Boolean {
        val msg = Message.obtain()
        msg.what = what
        return sendMessageDelayed(msg, delayMillis)
    }

    fun sendEmptyMessageAtTime(what: Int, uptimeMillis: Long): Boolean {
        val msg = Message.obtain()
        msg.what = what
        return sendMessageAtTime(msg, uptimeMillis)
    }

    /**
     * Enqueue a message into the message queue after all pending messages
     * before (current time + delayMillis). You will receive it in
     * [.handleMessage], in the thread attached to this handler.
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.  Note that a
     * result of true does not mean the message will be processed -- if
     * the looper is quit before the delivery time of the message
     * occurs then the message will be dropped.
     */
    fun sendMessageDelayed(msg: Message, delayMillis: Long): Boolean {
        var delayMillis = delayMillis
        if (delayMillis < 0) {
            delayMillis = 0
        }
        return sendMessageAtTime(msg, System.currentTimeMillis() + delayMillis)
    }

    fun sendMessageAtTime(msg: Message, uptimeMillis: Long): Boolean {
        val queue = mQueue
        if (queue == null) {
            val e = RuntimeException(
                "$this sendMessageAtTime() called with no mQueue"
            )
            return false
        }
        return enqueueMessage(queue, msg, uptimeMillis)
    }

    /**
     * Enqueue a message at the front of the message queue, to be processed on
     * the next iteration of the message loop.  You will receive it in
     * [.handleMessage], in the thread attached to this handler.
     * **This method is only for use in very special circumstances -- it
     * can easily starve the message queue, cause ordering problems, or have
     * other unexpected side-effects.**
     *
     * @return Returns true if the message was successfully placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     */
    fun sendMessageAtFrontOfQueue(msg: Message): Boolean {
        val queue = mQueue
        if (queue == null) {
            val e = RuntimeException(
                "$this sendMessageAtTime() called with no mQueue"
            )
            return false
        }
        return enqueueMessage(queue, msg, 0)
    }

    /**
     * Executes the message synchronously if called on the same thread this handler corresponds to,
     * or [pushes it to the queue][.sendMessage] otherwise
     *
     * @return Returns true if the message was successfully ran or placed in to the
     * message queue.  Returns false on failure, usually because the
     * looper processing the message queue is exiting.
     * @hide
     */
    fun executeOrSendMessage(msg: Message): Boolean {
        if (looper == Looper.myLooper()) {
            dispatchMessage(msg)
            return true
        }
        return sendMessage(msg)
    }

    private fun enqueueMessage(
        queue: MessageQueue, msg: Message,
        uptimeMillis: Long
    ): Boolean {
        msg.target = this

        if (mAsynchronous) {
            msg.isAsynchronous = true
        }
        return queue.enqueueMessage(msg, uptimeMillis)
    }

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    fun removeMessages(what: Int) {
        mQueue.removeMessages(this, what, null)
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     */
    fun removeMessages(what: Int, `object`: Any?) {
        mQueue.removeMessages(this, what, `object`)
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     *
     *
     * Similar to [.removeMessages] but uses object equality
     * ([Object.equals]) instead of reference equality (==) in
     * determining whether object is the message's obj'.
     *
     * @hide
     */
    fun removeEqualMessages(what: Int, `object`: Any?) {
        mQueue.removeEqualMessages(this, what, `object`)
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     */
    fun removeCallbacksAndMessages(token: Any?) {
        mQueue.removeCallbacksAndMessages(this, token)
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     *
     * @hide
     */
    fun removeCallbacksAndEqualMessages(token: Any?) {
        mQueue.removeCallbacksAndEqualMessages(this, token)
    }

    /**
     * Check if there are any pending posts of messages with code 'what' in
     * the message queue.
     */
    fun hasMessages(what: Int): Boolean {
        return mQueue.hasMessages(this, what, null)
    }

    /**
     * Return whether there are any messages or callbacks currently scheduled on this handler.
     * @hide
     */
    fun hasMessagesOrCallbacks(): Boolean {
        return mQueue.hasMessages(this)
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     */
    fun hasMessages(what: Int, `object`: Any?): Boolean {
        return mQueue.hasMessages(this, what, `object`)
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     *
     * @hide
     */
    fun hasEqualMessages(what: Int, `object`: Any?): Boolean {
        return mQueue.hasEqualMessages(this, what, `object`)
    }

    /**
     * Check if there are any pending posts of messages with callback r in
     * the message queue.
     */
    fun hasCallbacks(r: Runnable): Boolean {
        return mQueue.hasMessages(this, r, null)
    }


    override fun toString(): String {
        return ("Handler (" + javaClass.name + ") {"
                + Integer.toHexString(System.identityHashCode(this))
                + "}")
    }

    // if we can get rid of this method, the handler need not remember its loop
    // we could instead export a getMessageQueue() method...
    val looper: Looper
    val mQueue: MessageQueue
    val mCallback: Callback?
    val mAsynchronous: Boolean

    private class BlockingRunnable(private val mTask: Runnable) : Runnable {
        private var mDone = false

        override fun run() {
            try {
                mTask.run()
            } finally {
                synchronized(this) {
                    mDone = true
                    (this as Object).notifyAll()
                }
            }
        }

        fun postAndWait(handler: Handler, timeout: Long): Boolean {
            if (!handler.post(this)) {
                return false
            }

            synchronized(this) {
                if (timeout > 0) {
                    val expirationTime = System.currentTimeMillis() + timeout
                    while (!mDone) {
                        val delay = expirationTime - System.currentTimeMillis()
                        if (delay <= 0) {
                            return false // timeout
                        }
                        try {
                            (this as Object).wait(delay)
                        } catch (ex: InterruptedException) {
                        }
                    }
                } else {
                    while (!mDone) {
                        try {
                            (this as Object).wait()
                        } catch (ex: InterruptedException) {
                        }
                    }
                }
            }
            return true
        }
    }

    companion object {
        /*
     * Set this flag to true to detect anonymous, local or member classes
     * that extend this Handler class and that are not static. These kind
     * of classes can potentially create leaks.
     */
        private const val FIND_POTENTIAL_LEAKS = false
        private const val TAG = "Handler"
        private var MAIN_THREAD_HANDLER: Handler? = null

        /**
         * Create a new Handler whose posted messages and runnables are not subject to
         * synchronization barriers such as display vsync.
         *
         *
         * Messages sent to an async handler are guaranteed to be ordered with respect to one another,
         * but not necessarily with respect to messages from other Handlers.
         *
         * @see .createAsync
         * @param looper the Looper that the new Handler should be bound to
         * @return a new async Handler instance
         */
        fun createAsync(looper: Looper): Handler {
            if (looper == null) throw NullPointerException("looper must not be null")
            return Handler(looper, null, true)
        }

        /**
         * Create a new Handler whose posted messages and runnables are not subject to
         * synchronization barriers such as display vsync.
         *
         *
         * Messages sent to an async handler are guaranteed to be ordered with respect to one another,
         * but not necessarily with respect to messages from other Handlers.
         *
         * @see .createAsync
         * @param looper the Looper that the new Handler should be bound to
         * @return a new async Handler instance
         */
        fun createAsync(looper: Looper, callback: Callback): Handler {
            if (looper == null) throw NullPointerException("looper must not be null")
            if (callback == null) throw NullPointerException("callback must not be null")
            return Handler(looper, callback, true)
        }

        val main: Handler
            /** @hide
             */
            get() {
                if (MAIN_THREAD_HANDLER == null) {
                    MAIN_THREAD_HANDLER = Handler(Looper.mainLooper!!)
                }
                return MAIN_THREAD_HANDLER!!
            }

        /** @hide
         */
        fun mainIfNull(handler: Handler?): Handler {
            return handler ?: main
        }

        private fun getPostMessage(r: Runnable): Message {
            val m = Message.obtain()
            m.callback = r
            return m
        }

        private fun getPostMessage(r: Runnable, token: Any?): Message {
            val m = Message.obtain()
            m.obj = token
            m.callback = r
            return m
        }

        private fun handleCallback(message: Message) {
            message.callback?.run()
        }
    }
}
