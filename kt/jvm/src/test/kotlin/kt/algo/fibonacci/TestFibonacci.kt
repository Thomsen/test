package kt.algo.fibonacci

import org.junit.Test
import kotlin.test.assertEquals

class TestFibonacci {

//    lateinit var fibKeys: Array<Int>
//
//    lateinit var fibVals: Array<Int>
//
//    @Before
//    fun setupInit() {
//        fibKeys = arrayOf(-1, 0, 1, 2, 3, 4, 5, 6, 7 , 8 , 9)
//        fibVals = arrayOf( 0, 0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
//    }

    var fibKeys: Array<Int> =
        arrayOf(-1, 0, 1, 2, 3, 4, 5, 6, 7 , 8 , 9)

    var fibVals: Array<Int> =
        arrayOf( 0, 0, 1, 1, 2, 3, 5, 8, 13, 21, 34)


    @Test
    fun testFib() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], fib(key))
        }
    }

    @Test
    fun testFibTime() {
        codeRuntime {
            println(fib(40))
        }
    }

    @Test
    fun testFibVector() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], fibVector(key))
        }
    }

    @Test
    fun testFibVectorTime() {
        codeRuntime {
            println(fibVector(40))
        }
    }

    @Test
    fun testFibDp() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], fibMemorandumFromTopdown(key))
        }
    }

    @Test
    fun testFibDpReduce() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], fibDpReduce(key))
        }
    }

//    @Test
//    fun runBenchmarks() {
//        val options = OptionsBuilder()
//            .include(this.javaClass.name + ".*")
//            .mode(Mode.AverageTime)
//            .warmupTime(TimeValue.seconds(1))
//            .warmupIterations(6)
//            .threads(1)
//            .measurementIterations(6)
//            .forks(1)
//            .shouldFailOnError(true)
//            .shouldDoGC(true)
//            .build()
//
//        Runner(options).run()
//    }

    @Test
    fun testMyFib() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], Fib.fib(key))
        }
    }

    @Test
    fun testMyFibPrune() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], Fib.fibPrune(key))
        }
    }

    @Test
    fun testMyFibMemorandum() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], Fib.fibMemorandumFromTopdown(key))
        }
    }

    @Test
    fun testMyFibDynamicPlanning() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], Fib.fibDpFromDowntop(key))
        }
    }

    @Test
    fun testMyFibStateCompression() {
        fibKeys.forEachIndexed { index, key ->
            assertEquals(fibVals[index], Fib.fibDpStateCompression(key))
        }
    }
}