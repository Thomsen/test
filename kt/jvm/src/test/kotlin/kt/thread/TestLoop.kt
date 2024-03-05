package kt.thread

import kt.basic.printlnWithTime
import kt.thread.loop.Handler
import kt.thread.loop.Looper
import kt.thread.loop.Message
import kotlin.test.Test

class TestLoop {

    @Test
    fun `test prepare in thread`() {
        Thread {
            Looper.prepare()

            val handler = object : Handler(Looper.myLooper()) {
                override fun handleMessage(msg: Message) {
                    // process incoming messages here
                    printlnWithTime("msg.what : ${msg.what}")
                }
            }

            handler.sendMessage(Message())

            Looper.loop()
        }.start()
    }

    @Test
    fun `test main`() {
        Looper.prepare()

        // the main thread
        val handler = object: Handler(Looper.myLooper()) {
            override fun handleMessage(msg: Message) {
                // process incoming messages here
                printlnWithTime("msg.what : ${msg.what}")
            }
        }

        handler.postDelayed({
            printlnWithTime("handle delay task after 2000")
            // the child thread
            handler.sendMessage(Message())
        }, 2000)

        val msg2 = Message.obtain(handler)
        msg2.what = 100
        msg2.sendToTarget()

        handler.postDelayed({
            printlnWithTime("handle delay task after 1000")
        }, 200, 1000)

        Thread {
            Thread.sleep(5000)
            handler.looper.quit()
        }.start()


        printlnWithTime("message loop start")
        Looper.loop()


        printlnWithTime("message loop end")
    }
}