package kt.algo.backtrack

import org.junit.Test
import kotlin.test.assertEquals

class TestFullAlignment {

    @Test
    fun testFullAlign() {
        val fa = FullAlignment()
        val result = fa.permute(intArrayOf(1, 2, 3))
        assertEquals(6, result.size)
    }
}