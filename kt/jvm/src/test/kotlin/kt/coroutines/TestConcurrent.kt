package kt.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertSame

class TestConcurrent {
//    private suspend fun massiveRun(action: suspend () -> Unit) {
//        val n = 100
//        val k = 1000
//        val time = measureTimeMillis {
//            repeat(n) {
//                GlobalScope.launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//        println("Completed ${n * k} actions in $time ms")
//    }

    private suspend fun massiveRun(action: suspend () -> Unit) {
        val n = 100
        val k = 1000
        val time = measureTimeMillis {
            coroutineScope {
                repeat(n) {
                    launch {
                        repeat(k) {
                            action()
                        }
                    }
                }
            }
        }
        println("Completed ${n * k} actions in $time ms")
    }

    @Test
    fun testCounter() = runBlocking {
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                counter ++
            }
        }
        assertEquals(100000, counter, "counter concurrent")
    }

    // volatiles are of no help

    @Volatile
    var counter = 0

    @Test
    fun testCounterVolatile() = runBlocking {

        withContext(Dispatchers.Default) {
            massiveRun {
                counter ++
            }
        }
        assertEquals(100000, counter, "counter concurrent")
    }

    // thread-safe data structures

    var counterAtomic = AtomicInteger()

    @Test
    fun testCounterAtomic() = runBlocking {

        withContext(Dispatchers.Default) {
            massiveRun {
                counterAtomic.incrementAndGet()
            }
        }
        assertEquals(100000, counterAtomic.get(), "counter concurrent")
//        assertSame(100000, counterAtomic.get())
    }

    // thread confinement fine-grained

    var counterFineContext = newSingleThreadContext("CounterFine")
    var counterFine = 0

    @Test
    fun testCounterFine() = runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                withContext(counterFineContext) {
                    counterFine ++
                }
            }
        }
        assertEquals(100000, counterFine, "counter concurrent")
    }

    // thread confinement coarse-grained

    var counterCoarseContext = newSingleThreadContext("CounterCoarse")
    var counterCoarse = 0

    @Test
    fun testCounterCoarse() = runBlocking {
        withContext(counterCoarseContext) {
            massiveRun {
                counterCoarse ++
            }
        }
        assertEquals(100000, counterCoarse, "counter concurrent")
    }

    // mutual exclusion

    val mutex = Mutex()
    var counterMutex = 0

    @Test
    fun testCounterMutex() = runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                // locking in this is fine-grained
                mutex.withLock {
                    counterMutex ++
                }
            }
        }
        assertEquals(100000, counterMutex, "counter concurrent")
    }

    // actors

    sealed class CounterMsg {
        object IncCounter : CounterMsg()
        class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg()
    }

    fun CoroutineScope.counterActor() = actor<CounterMsg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is CounterMsg.IncCounter -> counter ++
                is CounterMsg.GetCounter -> msg.response.complete(counter)
            }
        }
    }

    @Test
    fun testCounterActor() = runBlocking {
        val counter = counterActor()
        withContext(Dispatchers.Default) {
            massiveRun {
                counter.send(CounterMsg.IncCounter)
            }
        }
        val response = CompletableDeferred<Int>()
        counter.send(CounterMsg.GetCounter(response))
        assertEquals(100000, response.await(), "counter concurrent")
        // shutdown the actor (assert solved method should be void)
        assert(counter.close())
    }


}


