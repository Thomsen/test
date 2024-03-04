package kt.thread.loop

class Message
/** Constructor (but the preferred way to get a Message is to call [Message.obtain()][.obtain]).
 */
{
    @kotlin.jvm.JvmField
    var what: Int = 0

    @kotlin.jvm.JvmField
    var arg1: Int = 0

    var arg2: Int = 0

    @kotlin.jvm.JvmField
    var obj: Any? = null

    var sendingUid: Int = UID_NONE

    var workSourceUid: Int = UID_NONE

    /*package*/
    var flags: Int = 0

    /**
     * Return the targeted delivery time of this message, in milliseconds.
     */
    @kotlin.jvm.JvmField
    var `when`: Long = 0

    /*package*/
    @kotlin.jvm.JvmField
    var target: Handler? = null

    /**
     * Retrieve callback object that will execute when this message is handled.
     * This object must implement Runnable. This is called by
     * the *target* [Handler] that is receiving this Message to
     * dispatch it.  If
     * not set, the message will be dispatched to the receiving Handler's
     * [Handler.handleMessage].
     */
    /*package*/
    @kotlin.jvm.JvmField
    var callback: Runnable? = null

    // sometimes we store linked lists of these things
    /*package*/
    @kotlin.jvm.JvmField
    var next: Message? = null


    /**
     * Return a Message instance to the global pool.
     *
     *
     * You MUST NOT touch the Message after calling this function because it has
     * effectively been freed.  It is an error to recycle a message that is currently
     * enqueued or that is in the process of being delivered to a Handler.
     *
     */
    fun recycle() {
        if (isInUse) {
            check(!gCheckRecycle) {
                ("This message cannot be recycled because it "
                        + "is still in use.")
            }
            return
        }
        recycleUnchecked()
    }

    /**
     * Recycles a Message that may be in-use.
     * Used internally by the MessageQueue and Looper when disposing of queued Messages.
     */
    fun recycleUnchecked() {
        // Mark the message as in use while it remains in the recycled object pool.
        // Clear out all other details.
        flags = FLAG_IN_USE
        what = 0
        arg1 = 0
        arg2 = 0
        obj = null
        sendingUid = UID_NONE
        workSourceUid = UID_NONE
        `when` = 0
        target = null
        callback = null

        synchronized(sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool
                sPool = this
                sPoolSize++
            }
        }
    }

    /**
     * Make this message like o.  Performs a shallow copy of the data field.
     * Does not copy the linked list fields, nor the timestamp or
     * target/callback of the original message.
     */
    fun copyFrom(o: Message) {
        this.flags = o.flags and FLAGS_TO_CLEAR_ON_COPY_FROM.inv()
        this.what = o.what
        this.arg1 = o.arg1
        this.arg2 = o.arg2
        this.obj = o.obj
        this.sendingUid = o.sendingUid
        this.workSourceUid = o.workSourceUid
    }

    fun setTarget(target: Handler?) {
        this.target = target
    }

    fun getTarget(): Handler? {
        return target
    }

    /** @hide
     */
    fun setCallback(r: Runnable?): Message {
        callback = r
        return this
    }


    /**
     * Chainable setter for [.what]
     *
     * @hide
     */
    fun setWhat(what: Int): Message {
        this.what = what
        return this
    }

    /**
     * Sends this Message to the Handler specified by [.getTarget].
     * Throws a null pointer exception if this field has not been set.
     */
    fun sendToTarget() {
        target!!.sendMessage(this)
    }

    var isAsynchronous: Boolean
        /**
         * Returns true if the message is asynchronous, meaning that it is not
         * subject to [Looper] synchronization barriers.
         *
         * @return True if the message is asynchronous.
         *
         * @see .setAsynchronous
         */
        get() = (flags and FLAG_ASYNCHRONOUS) != 0
        set(async) {
            flags = if (async) {
                flags or FLAG_ASYNCHRONOUS
            } else {
                flags and FLAG_ASYNCHRONOUS.inv()
            }
        }

    val isInUse: Boolean
        /*package*/
        get() = ((flags and FLAG_IN_USE) == FLAG_IN_USE)

    /*package*/
    fun markInUse() {
        flags = flags or FLAG_IN_USE
    }

    override fun toString(): String {
        return toString(System.currentTimeMillis())
    }

    fun toString(now: Long): String {
        val b = StringBuilder()
        b.append("{ when=")

        if (target != null) {
            if (callback != null) {
                b.append(" callback=")
                b.append(callback!!.javaClass.name)
            } else {
                b.append(" what=")
                b.append(what)
            }

            if (arg1 != 0) {
                b.append(" arg1=")
                b.append(arg1)
            }

            if (arg2 != 0) {
                b.append(" arg2=")
                b.append(arg2)
            }

            if (obj != null) {
                b.append(" obj=")
                b.append(obj)
            }

            b.append(" target=")
            b.append(target!!.javaClass.name)
        } else {
            b.append(" barrier=")
            b.append(arg1)
        }

        b.append(" }")
        return b.toString()
    }

    companion object {
        const val UID_NONE: Int = -1

        /*package*/
        const val FLAG_IN_USE: Int = 1 shl 0

        /** If set message is asynchronous  */ /*package*/
        const val FLAG_ASYNCHRONOUS: Int = 1 shl 1

        /** Flags to clear in the copyFrom method  */ /*package*/
        const val FLAGS_TO_CLEAR_ON_COPY_FROM: Int = FLAG_IN_USE

        /** @hide
         */
        val sPoolSync: Any = Any()
        private var sPool: Message? = null
        private var sPoolSize = 0

        private const val MAX_POOL_SIZE = 50

        private const val gCheckRecycle = true

        /**
         * Return a new Message instance from the global pool. Allows us to
         * avoid allocating new objects in many cases.
         */
        @kotlin.jvm.JvmStatic
        fun obtain(): Message {
            synchronized(sPoolSync) {
                if (sPool != null) {
                    val m = sPool
                    sPool = m!!.next
                    m.next = null
                    m.flags = 0 // clear in-use flag
                    sPoolSize--
                    return m
                }
            }
            return Message()
        }

        /**
         * Same as [.obtain], but copies the values of an existing
         * message (including its target) into the new one.
         * @param orig Original message to copy.
         * @return A Message object from the global pool.
         */
        fun obtain(orig: Message): Message? {
            val m = obtain()
            m!!.what = orig.what
            m.arg1 = orig.arg1
            m.arg2 = orig.arg2
            m.obj = orig.obj
            m.sendingUid = orig.sendingUid
            m.workSourceUid = orig.workSourceUid

            m.target = orig.target
            m.callback = orig.callback

            return m
        }

        /**
         * Same as [.obtain], but sets the value for the *target* member on the Message returned.
         * @param h  Handler to assign to the returned Message object's *target* member.
         * @return A Message object from the global pool.
         */
        fun obtain(h: Handler): Message {
            val m = obtain()
            m!!.target = h

            return m
        }

        /**
         * Same as [.obtain], but assigns a callback Runnable on
         * the Message that is returned.
         * @param h  Handler to assign to the returned Message object's *target* member.
         * @param callback Runnable that will execute when the message is handled.
         * @return A Message object from the global pool.
         */
        fun obtain(h: Handler?, callback: Runnable?): Message {
            val m = obtain()
            m!!.target = h
            m.callback = callback

            return m
        }

        /**
         * Same as [.obtain], but sets the values for both *target* and
         * *what* members on the Message.
         * @param h  Value to assign to the *target* member.
         * @param what  Value to assign to the *what* member.
         * @return A Message object from the global pool.
         */
        fun obtain(h: Handler, what: Int): Message {
            val m = obtain()
            m!!.target = h
            m.what = what

            return m
        }

        /**
         * Same as [.obtain], but sets the values of the *target*, *what*, and *obj*
         * members.
         * @param h  The *target* value to set.
         * @param what  The *what* value to set.
         * @param obj  The *object* method to set.
         * @return  A Message object from the global pool.
         */
        fun obtain(h: Handler, what: Int, obj: Any?): Message {
            val m = obtain()
            m!!.target = h
            m.what = what
            m.obj = obj

            return m
        }

        /**
         * Same as [.obtain], but sets the values of the *target*, *what*,
         * *arg1*, and *arg2* members.
         *
         * @param h  The *target* value to set.
         * @param what  The *what* value to set.
         * @param arg1  The *arg1* value to set.
         * @param arg2  The *arg2* value to set.
         * @return  A Message object from the global pool.
         */
        fun obtain(h: Handler, what: Int, arg1: Int, arg2: Int): Message {
            val m = obtain()
            m!!.target = h
            m.what = what
            m.arg1 = arg1
            m.arg2 = arg2

            return m
        }

        /**
         * Same as [.obtain], but sets the values of the *target*, *what*,
         * *arg1*, *arg2*, and *obj* members.
         *
         * @param h  The *target* value to set.
         * @param what  The *what* value to set.
         * @param arg1  The *arg1* value to set.
         * @param arg2  The *arg2* value to set.
         * @param obj  The *obj* value to set.
         * @return  A Message object from the global pool.
         */
        fun obtain(
            h: Handler, what: Int,
            arg1: Int, arg2: Int, obj: Any?
        ): Message {
            val m = obtain()
            m!!.target = h
            m.what = what
            m.arg1 = arg1
            m.arg2 = arg2
            m.obj = obj

            return m
        }

        /** @hide
         */
        fun updateCheckRecycle(targetSdkVersion: Int) {
//        if (targetSdkVersion < Build.VERSION_CODES.LOLLIPOP) {
//            gCheckRecycle = false;
//        }
        }
    }
}
