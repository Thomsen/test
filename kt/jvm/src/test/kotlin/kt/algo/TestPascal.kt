package kt.algo

import org.junit.Test


class TestPascal {
    private fun pascal() = generateSequence(listOf(1)) { prev ->
        listOf(1) + (1..prev.lastIndex).map {
            prev[it - 1] + prev[it]
        } + listOf(1)
    }

    @Test
    fun testPascal() {
        pascal().take(10).forEach(::println)
    }
}