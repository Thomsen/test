package kt.thread.loop

import kt.basic.printlnWithTime



class LoopThread: Thread() {

    private var mHandler: Handler? = null

    override fun run() {
        Looper.prepare()

        mHandler = object : Handler(Looper.myLooper()) {
            override fun handleMessage(msg: Message) {
                // process incoming messages here
                printlnWithTime("msg.what : ${msg.what}")
            }
        }

        mHandler?.sendMessage(Message())

        Looper.loop()
    }

}

fun main(args: Array<String>) {

//    val loopThread = LoopThread()
//    loopThread.start()

    Looper.prepare()
    val handler = object: Handler(Looper.myLooper()) {
        override fun handleMessage(msg: Message) {
            // process incoming messages here
            printlnWithTime("msg.what : ${msg.what}")
        }
    }

    val delayedTask = Runnable {
        printlnWithTime("handle delay task")
        handler.sendMessage(Message())
    }
    handler.postDelayed(delayedTask, 2000)


    printlnWithTime("start message loop")
    Looper.loop()


    printlnWithTime("message loop end")


}