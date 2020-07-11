package kt.coroutines

import kotlinx.coroutines.*
import org.junit.Test
import java.io.IOException

class TestException {

    @Test
    fun testMain() {
        runBlocking {
            val job = GlobalScope.launch {
                println("Throwing exception from launch")
                throw IndexOutOfBoundsException()
            }
            job.join()
            println("Joined failed job")
            val deferred = GlobalScope.async {
                println("Throwing exception from async")
                throw ArithmeticException()
            }
            try {
                deferred.await()
                println("Unreached")
            } catch (e: ArithmeticException) {
                println("Caught ArithmeticException")
            }
        }
    }

    @Test
    fun testMainAsync() {
        runBlocking {
            try {
                val deferred1 = GlobalScope.async {
                    delay(1000)
                    println("print deferred1 complete")
                }
                val deferred = GlobalScope.async {
                    println("Throwing exception from async")
                    throw ArithmeticException()
                }

                deferred.await()
                println("Unreached")
            } catch (e: ArithmeticException) {
                println("Caught ArithmeticException")
            }
        }
    }

    @Test
    fun testExceptionHandler() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
                throw AssertionError()
            }
            val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
                throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
            }
            joinAll(job, deferred)
        }
    }

    @Test
    fun testCancellation() {
        runBlocking {
            val job = launch {
                val child = launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("Child is cancelled")
                    }
                }
                yield()
                println("Cancelling child")
                child.cancel()
                child.join()
                yield()
                println("Parent is not cancelled")
            }
            job.join()
        }
    }

    @Test
    fun testCancellationAndException() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                launch { // the first child
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        withContext(NonCancellable) {
                            println("Children are cancelled, but exception is not handled until all children terminate")
                            delay(100)
                            println("The first child finished its non cancellable block")
                        }
                    }
                }
                launch { // the second child
                    delay(10)
                    println("Second child throws an exception")
                    throw ArithmeticException()
                }
            }
            job.join()
        }
    }

    @Test
    fun testExceptionAggregation() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
            }
            val job = GlobalScope.launch(handler) {
                launch {
                    try {
                        delay(Long.MAX_VALUE) // it gets cancelled when another sibling fails with IOException
                    } finally {
                        throw ArithmeticException() // the second exception
                    }
                }
                launch {
                    delay(100)
                    throw IOException() // the first exception
                }
                delay(Long.MAX_VALUE)
            }
            job.join()
        }
    }

    @Test
    fun testExceptionTransparent() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            val job = GlobalScope.launch(handler) {
                val inner = launch { // all this stack of coroutines will get cancelled
                    launch {
                        launch {
                            throw IOException() // the original exception
                        }
                    }
                }
                try {
                    inner.join()
                } catch (e: CancellationException) {
                    println("Rethrowing CancellationException with original cause")
                    throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
                }
            }
            job.join()
        }
    }

    @Test
    fun testSupervisionJob() {
        runBlocking {
            val supervisor = SupervisorJob()
            with(CoroutineScope(coroutineContext + supervisor)) {
                // launch the first child -- its exception is ignored for this example (don't do this in practice!)
                val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
                    println("The first child is failing")
                    throw AssertionError("The first child is cancelled")
                }
                // launch the second child
                val secondChild = launch {
                    firstChild.join()
                    // Cancellation of the first child is not propagated to the second child
                    println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        // But cancellation of the supervisor is propagated
                        println("The second child is cancelled because the supervisor was cancelled")
                    }
                }
                // wait until the first child fails & completes
                firstChild.join()
                println("Cancelling the supervisor")
                supervisor.cancel()
                secondChild.join()
            }
        }
    }

    @Test
    fun testSupervisorScope() {
        runBlocking {
            try {
                supervisorScope {
                    val child = launch {
                        try {
                            println("The child is sleeping")
                            delay(Long.MAX_VALUE)
                        } finally {
                            println("The child is cancelled")
                        }
                    }
                    // Give our child a chance to execute and print using yield
                    yield()
                    println("Throwing an exception from the scope")
                    throw AssertionError()
                }
            } catch(e: AssertionError) {
                println("Caught an assertion error")
            }
        }
    }

    @Test
    fun testSupervisorHandler() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
            supervisorScope {
                val child = launch(handler) {
                    println("The child throws an exception")
                    throw AssertionError()
                }
                println("The scope is completing")
            }
            println("The scope is completed")
        }
    }
}