package kt.thread

import kt.basic.printlnWithTime
import java.util.concurrent.CompletableFuture


object ThreadLocalWithAsync {
    private val threadLocal = ThreadLocal<Int>()

//    private val threadLocal = LocalData<Int>()
    // maybe
    // [2024-03-04 15:46:48] ForkJoinPool.commonPool-worker-5: 300
    // [2024-03-04 15:46:48] ForkJoinPool.commonPool-worker-3: 300

    @JvmStatic
    fun main(args: Array<String>) {
        // async execute
        val future200 = CompletableFuture.runAsync {
            try {
                // set local value
                threadLocal.set(2 * 100)

                Thread.sleep(1000)

                printlnWithTime(Thread.currentThread().name + ": " + threadLocal.get())
            } finally {
                // clear thread local
                threadLocal.remove()
            }
        }

        val future300 = CompletableFuture.runAsync {
            try {
                // set local value
                threadLocal.set(3 * 100)

                printlnWithTime(Thread.currentThread().name + ": " + threadLocal.get())
            } finally {
                // clear thread local
                threadLocal.remove()
            }
        }

        future300.join()

        // block the main thread to wait the async
        future200.join()


    }
}