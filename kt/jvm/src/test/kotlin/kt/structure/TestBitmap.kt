package kt.structure

import org.junit.Test
import kotlin.test.assertEquals

class TestBitmap {

    @Test
    fun textOne() {
        // capacity > n
        val bitmap = bitmap(300)
        bitmap.add(233)
        assertEquals(true, bitmap.contain(233))
    }
}