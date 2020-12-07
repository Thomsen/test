package kt.algo

import kt.sort.quickSort
import org.junit.Test
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class TestSort {

    @Test
    fun testQuick() {
        val values = listOf(2, 3, 5, 1).quickSort()
        assertEquals(listOf(1, 2, 3, 5), values)
    }

    @Test
    fun testQuickPerf() {
        val random = Random()
        listOf(100, 100_000, 1_000_000, 10_000_000)
            .asSequence()
            .map { (1..it).map { random.nextInt(10000000000.toInt()) } }
            .forEach {list: List<Int> ->
                println("java stdlib sorting of ${list.size} elements took ${measureTimeMillis { list.sorted() }}")
                println("quickSort sorting of ${list.size} elements took ${measureTimeMillis { list.quickSort() }}")
            }
    }
}