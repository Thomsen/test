package kt.collection

import org.junit.Test
import kotlin.test.assertEquals

class TestAggregate {

    private val numbers = listOf(5, 2, 10, 4)

    @Test
    fun `test reduce sum`() {
        val sum = numbers.reduce {
                sum, element ->
            run {
                println("sum: $sum, and element: $element")
                sum + element
            }
        }
        assertEquals(21, sum)
    }

    @Test
    fun `test fold sum`() {
        val foldSum = numbers.fold(0) {
                sum, element ->
            run {
                println("sum: $sum, and element: $element")
                sum + element
            }
        }
        assertEquals(21, foldSum)
    }

    @Test
    fun `test fold right sum`() {
        val foldRightSum = numbers.foldRight(0) {
                element, sum ->
            run {
                println("sum: $sum, and element: $element")
                sum + element
            }
        }
        assertEquals(21, foldRightSum)
    }

    @Test
    fun `test fold index sum`() {
        val foldIndexSum = numbers.foldIndexed(0) {
                idx, sum, element ->
            run {
                println("sum: $sum, and element: $element")
                if (idx % 2 == 0) {
                    sum + element
                } else {
                    sum
                }
            }
        }
        assertEquals(15, foldIndexSum)
    }
}