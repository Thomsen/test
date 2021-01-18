package kt.algo.fibonacci

import org.junit.Test
import kotlin.test.assertEquals

class TestFibonacci {

    @Test
    fun testFib() {
        assertEquals(3, fib(4))
    }

    @Test
    fun testFibTime() {
        codeRuntime {
            println(fib(40))
        }
    }

    @Test
    fun testFibVector() {
        assertEquals(0, fibVector(-1))
        assertEquals(0, fibVector(0))
        assertEquals(1, fibVector(1))
        assertEquals(1, fibVector(2))
        assertEquals(2, fibVector(3))
        assertEquals(3, fibVector(4))
        assertEquals(5, fibVector(5))
        assertEquals(8, fibVector(6))
    }

    @Test
    fun testFibVectorTime() {
        codeRuntime {
            println(fibVector(40))
        }
    }

    @Test
    fun testFibDp() {
        assertEquals(0, fibDp(-1))
        assertEquals(0, fibDp(0))
        assertEquals(1, fibDp(1))
        assertEquals(1, fibDp(2))
        assertEquals(2, fibDp(3))
        assertEquals(3, fibDp(4))
        assertEquals(5, fibDp(5))
        assertEquals(8, fibDp(6))
    }

    @Test
    fun testFibDpReduce() {
        assertEquals(0, fibDpReduce(-1))
        assertEquals(0, fibDpReduce(0))
        assertEquals(1, fibDpReduce(1))
        assertEquals(1, fibDpReduce(2))
        assertEquals(2, fibDpReduce(3))
        assertEquals(3, fibDpReduce(4))
        assertEquals(5, fibDpReduce(5))
        assertEquals(8, fibDpReduce(6))
    }
}