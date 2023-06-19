package kt.collection

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestFilter {

    @Test
    fun `test filter empty`() {
        var open1 = listOf(1, 2, 3, 4).filter {
            it == 10
        }
        assertTrue(open1.isEmpty())
    }

    @Test
    fun `test filter not empty`() {
        var open = listOf(1, 2, 3, 4).filter {
            it == 2
        }
        assertTrue(open.isNotEmpty())
    }
}