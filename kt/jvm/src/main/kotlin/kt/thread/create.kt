package kt.thread

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ThreadCreate: Thread() {
    override fun run() {
        super.run()
        println("thread 1")
    }
}


fun create() {
    val th1 = ThreadCreate()
    th1.start()

    // Thread(Runnable) lambda
    val th2 = Thread {
        Thread.sleep(2000)
        println("thread 2")
    }
    th2.start()

    val executor = Executors.newSingleThreadExecutor()
    val future: Future<String> = executor.submit(Callable<String> {
        Thread.sleep(3000)
        "thread 3"
    })
    println(future.get())
}