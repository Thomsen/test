package kt.math

import org.junit.Test
import kotlin.test.assertEquals

class TestFloor {

    @Test
    fun `test floor`() {
        assertEquals(20, Math.floor(1245 / 60.0).toInt())
    }
}