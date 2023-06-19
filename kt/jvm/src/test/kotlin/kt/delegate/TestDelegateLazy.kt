package kt.delegate

import kotlin.test.Test
import kotlin.test.assertEquals


class TestDelegateLazy {
    var count = 0
    val myProp: String by LazyProperty {
        count ++
        "prop"
    }

    @Test
    fun `test exec 1`() {
        assertEquals("prop", myProp)
        assertEquals(1, count)
    }

    @Test
    fun `test exec 2`() {
        assertEquals("prop", myProp)
        assertEquals(1, count)
    }

    @Test
    fun `test exec 3`() {
        assertEquals("prop", myProp)
        assertEquals(1, count)
    }

}