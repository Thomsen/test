package kt.advance

import kotlinx.coroutines.flow.combine
import org.junit.Test

class TestInfix {

    fun <T> List<T>.combineWith(other: List<T>): List<T> {
        val combined: MutableList<T> = mutableListOf()
        combined.addAll(this)
        combined.addAll(other)

        return combined
    }

    @Test
    fun testCombineWith() {
        val all: List<Int> = listOf<Int>(1, 2, 3).combineWith(listOf(5, 6))
        println(all)
    }

    infix fun <T> List<T>.combineWith2(other: List<T>): List<T> {
        val combined: MutableList<T> = mutableListOf()
        combined.addAll(this)
        combined.addAll(other)

        return combined
    }

    @Test
    fun testCombineWith2() {
        val all: List<Int> = listOf<Int>(1, 2, 3) combineWith2 listOf(5, 6)
        println(all)
    }

    // must be member functions or extension functions
    // must have a single parameter (no vararg, no default value)
    // mapOf( key to value)
    infix fun <T> List<T>.improvedCombinedWith(other: List<T>): List<T> {
        return this + other
    }

}