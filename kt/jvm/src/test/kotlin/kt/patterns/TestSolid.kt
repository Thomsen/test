package kt.patterns

import kt.patterns.solid.*
import org.junit.Test
import kotlin.test.assertEquals

class TestSolid {

    @Test
    fun testArea1() {
       val shapes = arrayListOf(Rectangle0(3, 4), Circle0(2f))
        val factory = AreaFactory0()
        assertEquals((12 + 4 * Math.PI), factory.calculateArea(shapes))
    }

    @Test
    fun testArea2() {
        val shapes = arrayListOf(Rectangle(3, 4), Circle(2f))
        val factory = AreaFactory()
        assertEquals((12 + 4 * Math.PI), factory.calculateArea(shapes))
    }
}