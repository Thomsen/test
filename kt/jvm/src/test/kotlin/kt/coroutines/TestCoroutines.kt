package kt.coroutines

import kotlinx.coroutines.*
import kt.basic.*
import org.junit.Test
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class TestCoroutines {

    @Test
    fun testGlobal() {
        exampleGlobal()
    }

    @Test
    fun testBlocking() {
        exampleBlocking()
    }

    @Test
    fun testCoroutineScope() {
        runBlocking {
            exampleCoroutineScope()
        }
    }

    @Test
    fun testCoroutineScope2() {
        runBlocking {
            exampleCoroutineScope2()
        }
    }

    @Test
    fun testBlockingAndScope() {
        exampleBlockingAndScope()
    }

    @Test
    fun exampleThread() {
        thread {
            Thread.sleep(1000)
            println("five")
        }
        thread {
            Thread.sleep(3000)
            println("four")
        }
        println("one")
        runBlocking {
            printDelayed("two")
        }
//        Thread.sleep(4000)
        println("three")

        Thread.sleep(3000)
    }

    @Test
    fun exampleBlockingDispatcher() {
        runBlocking(Dispatchers.Default) {
            println("one - from thread ${Thread.currentThread().name}")
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }
        println("three - from thread ${Thread.currentThread().name}")

    }

    @Test
    fun exampleLaunchGlobal() = runBlocking {
        println("global one - from thread ${Thread.currentThread().name}")

        GlobalScope.launch {
            printDelayed("global two - from thread ${Thread.currentThread().name}")
        }

        println("global three - from thread ${Thread.currentThread().name}")

        delay(2000)
    }

    @Test
    fun exampleLaunchGlobalWaiting() = runBlocking {
        println("global one - from thread ${Thread.currentThread().name}")

        val job = GlobalScope.launch {
            printDelayed("global two - from thread ${Thread.currentThread().name}")
        }

        println("global three - from thread ${Thread.currentThread().name}")

        job.join()
    }

    @Test
    fun exampleLaunchCoroutineScope() = runBlocking {
        println("launch one - from thread ${Thread.currentThread().name}")

        launch {
            printDelayed("launch two - from thread ${Thread.currentThread().name}")
        }

        println("launch three - from thread ${Thread.currentThread().name}")
    }

    @Test
    fun exampleLaunchCoroutineScope2() = runBlocking {
        println("launch one - from thread ${Thread.currentThread().name}")

        launch(Dispatchers.Default) {
            printDelayed("launch two - from thread ${Thread.currentThread().name}")
        }

        println("launch three - from thread ${Thread.currentThread().name}")
    }

    @Test
    fun exampleLaunchCoroutineScope3() = runBlocking {
        println("launch one - from thread ${Thread.currentThread().name}")

        launch(Dispatchers.IO) {
            printDelayed("launch two - from thread ${Thread.currentThread().name}")
        }

        println("launch three - from thread ${Thread.currentThread().name}")
    }

    @Test
    fun exampleLaunchCoroutineScope4() = runBlocking {
        println("launch one - from thread ${Thread.currentThread().name}")

        launch(Dispatchers.Main) {
            printDelayed("launch two - from thread ${Thread.currentThread().name}")
        }

        println("launch three - from thread ${Thread.currentThread().name}")
    }

    @Test
    fun exampleLaunchCoroutineScope5() = runBlocking {
        println("launch one - from thread ${Thread.currentThread().name}")

        val customDispacher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
        launch(customDispacher) {
            printDelayed("launch two - from thread ${Thread.currentThread().name}")
        }

        println("launch three - from thread ${Thread.currentThread().name}")

        (customDispacher.executor as ExecutorService).shutdown()
    }

    @Test
    fun exampleAsyncAwait() = runBlocking {
        val startTime = System.currentTimeMillis()
        val deferred1 = async {
            calculatedHardThings(10)
        }.await()

        val deferred2 = async {
            calculatedHardThings(20)
        }.await()

        val deferred3 = async {
            calculatedHardThings(30)
        }.await()

        val sum = deferred1 + deferred2 + deferred3
        println("async/await result = $sum")

        val endTime = System.currentTimeMillis()
        println("Time taken ${endTime - startTime}")
    }

    @Test
    fun exampleAsyncAwait2() = runBlocking {
        val startTime = System.currentTimeMillis()
        val deferred1 = async {
            calculatedHardThings(10)
        }

        val deferred2 = async {
            calculatedHardThings(20)
        }

        val deferred3 = async {
            calculatedHardThings(30)
        }

        val sum = deferred1.await() + deferred2.await() + deferred3.await()
        println("async/await result = $sum")

        val endTime = System.currentTimeMillis()
        println("Time taken ${endTime - startTime}")
    }

    @Test
    fun exampleWithContext() = runBlocking {
        val startTime = System.currentTimeMillis()
        val deferred1 = withContext((Dispatchers.Default)) {
            calculatedHardThings(10)
        }

        val deferred2 = withContext(Dispatchers.Default) {
            calculatedHardThings(20)
        }

        val deferred3 = withContext(Dispatchers.Default) {
            calculatedHardThings(30)
        }

        val sum = deferred1 + deferred2 + deferred3
        println("withContext result = $sum")

        val endTime = System.currentTimeMillis()
        println("Time taken ${endTime - startTime}")
    }

    suspend fun printDelayed(msg: String) {
        delay(1000)
        println(msg)
    }

    suspend fun calculatedHardThings(startNum: Int): Int {
        delay(1000)
        return startNum * 10
    }

    fun testJob() {
        //val scope = CoroutineScope(Dispatchers.Main + Job() + handler)
    }

    fun testSupervisorJob() {
        //val scope = CoroutineScope(Dispatchers.Main + SupervisorJob() + handler)
    }

}