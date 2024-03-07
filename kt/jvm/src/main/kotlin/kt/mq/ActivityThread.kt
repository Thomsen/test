package kt.mq

import kt.basic.printlnWithTime
import kt.mq.loop.Handler
import kt.mq.loop.Looper
import kt.mq.loop.Message

class ActivityThread {

    companion object {
        const val QUIT = -1
    }

    class H: Handler(Looper.myLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            printlnWithTime("${this.looper} msg.what: ${msg.what}")

            when (msg.what) {
                QUIT -> looper.quit()
//                else -> printlnWithTime("please handle ${msg.what}")
            }
        }
    }

    @Volatile
    var sMainThreadHandler: Handler? = H()


    fun sendEmptyMessage(what: Int): Boolean {
        sMainThreadHandler?.sendEmptyMessage(what)
        return false
    }

}
