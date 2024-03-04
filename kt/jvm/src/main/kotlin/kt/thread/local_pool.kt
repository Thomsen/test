package kt.thread

import kt.basic.printlnWithTime
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit


class TransactionTask(private val transactionId: Int) : Runnable {
    companion object {
        // ThreadLocal is used with caution for
        //    thread pooling
        //    async programing
        // remove after used
        val transactionIdThreadLocal: ThreadLocal<Int> = ThreadLocal()
//        val transactionIdThreadLocal: LocalData<Int> = LocalData()

        // test thread local
        val testThreadLocal: ThreadLocal<Int> = ThreadLocal()
    }

    override fun run() {
        try {
            // set the test value if transactionId == 1
            if (transactionId == 1) {
                testThreadLocal.set(100)
            }
            // Set the transaction ID for the current thread
            transactionIdThreadLocal.set(transactionId)

            if (transactionId % 2 == 0) {
                Thread.sleep(1000)
            }

            // Simulate some work with the transaction ID
            printlnWithTime("Thread ${Thread.currentThread().name} processing transaction with ID:" +
                    " ${transactionIdThreadLocal.get()}, and test value: ${testThreadLocal.get()}")
            // Simulate work by sleeping for a random amount of time
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000, 5000).toLong())
        } finally {
            // Clean up the thread local to prevent memory leak in a thread pool scenario
            transactionIdThreadLocal.remove()
            // the test value need remove
            testThreadLocal.remove()
        }
    }
}

fun main() {
    val threadPool = Executors.newFixedThreadPool(3)
//    val threadPool = Executors.newCachedThreadPool()
//    val threadPool = Executors.newSingleThreadExecutor()
//    val threadPool = Executors.newScheduledThreadPool(3)
//    val threadPool = Executors.newWorkStealingPool()

    // Submit multiple tasks to the thread pool
    for (i in 1..10) {
        threadPool.submit(TransactionTask(i))
    }

    // Shutdown the thread pool after executing all tasks
    threadPool.shutdown()
    threadPool.awaitTermination(1, TimeUnit.MINUTES)
}