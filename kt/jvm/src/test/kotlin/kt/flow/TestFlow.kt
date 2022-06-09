package kt.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kt.parser.num
import org.junit.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestFlow {

    fun simple(): Flow<Int> = flow {
        println("simple flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    @Test
    fun testBasic() = runBlocking<Unit> {
        println("Calling simple function...")
        val flow = simple()
        println("Calling collect...")
        flow.collect { println(it) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }

    @Test
    fun testCancelBasic() = runBlocking {
        println("Calling simple timeout ...")
        withTimeoutOrNull(250) {
            simple().collect { println(it) }
        }
        // only print 1 2 and done
        println("Done")
    }

    suspend fun performRequest(request: Int): String {
        delay(1000) //
        return "response $request"
    }

    @Test
    fun testMap() = runBlocking {
        (1..3).asFlow()
            .map { request -> performRequest(request) }
            .collect { response -> println(response) }
    }

    @Test
    fun testTransform() = runBlocking {
        (1..3).asFlow()
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }
            .collect { response ->
                println(response)
            }
    }

    @Test
    fun testTask() = runBlocking {
        (1..3).asFlow()
            .transform { emit("Making request $it") }
            .take(2)
            .collect { println(it) }
    }

    @Test
    fun testReduce() = runBlocking {
        val sum = (1..5).asFlow()
            .map { it * it }
            .reduce {a, b -> a + b}
        // 1 + 4 + 9 + 16 + 25
        assertEquals(55, sum, "reduce sum error")
    }

    @Test
    fun testBufferBefore() = runBlocking {
        val time = measureTimeMillis {
            simple().collect {
                delay(300)
                println(it)
            }
        }
        println("collected in $time ms")
    }

    @Test
    fun testBufferAfter() = runBlocking {
        val time = measureTimeMillis {
                simple().buffer()
                .collect {
                delay(300)
                println(it)
            }
        }
        println("collected in $time ms")
    }

    @Test
    fun testConflate() = runBlocking {
        val time = measureTimeMillis {
            simple().conflate()
                .collect {
                    delay(300)
                    println(it)
                }
        }
        // 1 3 7xx ms
        println("collected in $time ms")
    }

    @Test
    fun testCollectLatest() = runBlocking {
        val time = measureTimeMillis {
            simple()
                .collectLatest {
                    delay(300)
                    println(it)
                }
        }
        // 3 7xx ms
        println("collected in $time ms")
    }

    @Test
    fun testZip() = runBlocking {
        val nums = (1..5).asFlow()
        val strs = flowOf("one" , "two", "three")
        nums.zip(strs) { a, b -> "$a -> $b"}
            .collect { println(it) }
    }

    @Test
    fun testZip2() = runBlocking {
        val nums = (1..5).asFlow().onEach { delay(100) }
        val strs = flowOf("one", "two", "three").onEach { delay(400) }
        val startTime = System.currentTimeMillis()
        nums.zip(strs) { a, b -> "$a -> $b" }
            .collect { println("zip $it at ${System.currentTimeMillis() - startTime} ms from start") }
    }

    @Test
    fun testCombine() = runBlocking {
        val nums = (1..5).asFlow().onEach { delay(100) }
        val strs = flowOf("one", "two", "three").onEach { delay(400) }
        val startTime = System.currentTimeMillis()
        nums.combine(strs) { a, b -> "$a -> $b" }
            .collect { println("combine $it at ${System.currentTimeMillis() - startTime} ms from start") }
    }

    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")
        delay(500) // 等待 500 毫秒
        emit("$i: Second")
    }

    @Test
    fun testFlatMapConcat() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { requestFlow(it) }
            .collect { value ->
                println("flatMapConcat $value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    @Test
    fun testFlatMapMerge() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach { delay(100) }
            .flatMapMerge { requestFlow(it) }
            .collect { value ->
                println("flatMapMerge $value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    @Test
    fun testFlatMapLatest() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach { delay(100) }
            .flatMapLatest { requestFlow(it) }
            .collect { value ->
                println("flatMapLatest $value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    @Test
    fun testTryCommand() = runBlocking {
        try {
            simple().collect { value ->
                check(value <= 1) { "Collected $value" }
                println(value)
            }
        } catch (e: Throwable) {
            println("Caught $e")
        }
    }

    @Test
    fun testTryDeclarative() = runBlocking {
        simple()
            .onEach { value ->
                check(value <= 1) { "Collected $value" }
                println(value)
            }
            .catch { e -> println("Caught $e") }
            .collect()
    }

    @Test
    fun testTry2() = runBlocking {
        simple()
            .catch { e -> println("Caught $e") } // catching upstream exceptions only
            .collect { value ->
                check(value <= 1) { "Collected $value" }
                println(value)
            }
    }

    @Test
    fun testCompletion() = runBlocking {
        simple()
            .onCompletion { println("Done") }
            .collect { value -> println(value) }
    }

    @Test
    fun testLaunchIn() = runBlocking {
        simple()
            .onEach { event -> println("Event: $event") }
            .launchIn(this) // <--- 在单独的协程中执行流
        println("Done")
    }
}