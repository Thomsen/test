package kt.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.test.assertEquals

class TestPowerful {

    @Test
    fun acceptTestCases() {
        assertEquals(1, 1)
    }

    @Test
    fun testMagnitude() {
        val times10 = magnitude(10)
        val times50 = magnitude(50)

        times10(5) shouldBeEqualTo 50
        times50(1) shouldBeEqualTo 50
    }

    @Test
    fun testMagnitudePower() {
        val times10 = magnitudePower(10)
        val times50 = magnitudePower(50)

        5.times10() shouldBeEqualTo 50
        2.times50() shouldBeEqualTo 100
    }

    @Test
    fun testSimple() {
        val array = "hello test power".split(" ")?.map { it.length }
        var sum = 0
        for (num in array) {
            sum += num
        }
        sum shouldBeEqualTo 14
    }

    @Test
    fun testAcceptCase() {
        acceptTestCases {
//            val array = readLine()?.split(" ")?.map { it.toInt() } ?: listOf()
            val array = "hello test power".split(" ")?.map { it.length }
            var sum = 0
            for (num in array) {
                sum += num
            }
            sum shouldBeEqualTo 14
        }
    }

    @Test
    fun testHtml() {
        htmlContent()
    }

}