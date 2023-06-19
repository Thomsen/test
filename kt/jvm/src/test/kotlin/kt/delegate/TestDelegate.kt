package kt.delegate

import org.junit.Test
import kotlin.test.assertEquals

class TestDelegate {

    @Test
    fun testDerived() {
        val b = BaseImpl(10)
        val derived = Derived(b)
        assertEquals("BaseImpl: x = 10", derived.printMessage())
        assertEquals("BaseImpl: x = 10\n", derived.printMessageLine())

        assertEquals("Message of Derived", derived.message)
    }

}