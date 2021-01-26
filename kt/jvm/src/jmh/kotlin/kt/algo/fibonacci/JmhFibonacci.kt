package kt.algo.fibonacci

import org.junit.Before
import org.junit.Test
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.TimeValue
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.Options


// Benchmark classes should not be final -- open class
// State for dead code
@State(Scope.Thread)
open class JmhFibonacci {

    private val testObject = TestFibonacci()

    @Benchmark
//    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    fun testMyFib() {
        testObject.testMyFib()
    }

    @Benchmark
//    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    fun testMyFibPrune() {
        testObject.testMyFibPrune()
    }

    @Benchmark
    fun testMyFibMemo() {
        testObject.testMyFibMemorandum()
    }

    @Benchmark
    fun testMyFibDp() {
        testObject.testMyFibDynamicPlanning()
    }

    companion object {
        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val opt: Options = OptionsBuilder()
                .include(".*" + JmhFibonacci::class.java.getSimpleName() + ".*")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build()
            Runner(opt).run()
        }
    }

}