package kt.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class TestSame {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    // runBlocking Method testSomeUI() should be void
    @Test
    fun testSomeUI() = runTest {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            // ...
            val r = add(3, 4)
            assertEquals(7, r)
        }
    }

    @Test
    fun testFoo(): TestResult {
        // initialize some test state
        return runTest {
            // code under test
        }
    }

    @Test
    fun testHanging() = runTest {
        // Test automatically time out after 10 seconds.
        CompletableDeferred<Unit>().await() // will hang forever
    }

    @Test
    fun testTakingALongTime() = runTest(timeout = 30.seconds) {
        val result = withContext(Dispatchers.Default) {
            delay(20.seconds) // this delay is not in the test dispatcher and will not be skipped
            3
        }
        assertEquals(3, result)
    }

    @Test
    fun testWithMultipleDelays() = runTest {
        launch {
            delay(1_000)
            println("1. $currentTime") // 1000
            delay(200)
            println("2. $currentTime") // 1200
            delay(2_000)
            println("4. $currentTime") // 3200
        }
        val deferred = async {
            delay(3_000)
            println("3. $currentTime") // 3000
            delay(500)
            println("5. $currentTime") // 3500
        }
        deferred.await()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun testFooVirtualTime() = runTest {
        launch {
            val workDuration = testScheduler.timeSource.measureTime {
                println(1)   // executes during runCurrent()
                delay(1_000) // suspends until time is advanced by at least 1_000
                println(2)   // executes during advanceTimeBy(2_000)
                delay(500)   // suspends until the time is advanced by another 500 ms
                println(3)   // also executes during advanceTimeBy(2_000)
                delay(5_000) // will suspend by another 4_500 ms
                println(4)   // executes during advanceUntilIdle()
            }
            assertEquals(6500.milliseconds, workDuration) // the work took 6_500 ms of virtual time
        }
        // the child coroutine has not run yet
        testScheduler.runCurrent()
        // the child coroutine has called println(1), and is suspended on delay(1_000)
        testScheduler.advanceTimeBy(2.seconds) // progress time, this will cause two calls to `delay` to resume
        // the child coroutine has called println(2) and println(3) and suspends for another 4_500 virtual milliseconds
        testScheduler.advanceUntilIdle() // will run the child coroutine to completion
        assertEquals(6500, currentTime) // the child coroutine finished at virtual time of 6_500 milliseconds
    }
}