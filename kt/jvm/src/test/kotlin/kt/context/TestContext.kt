package kt.context

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestContext {

    @Test
    fun `test dp 2 px`() {
        val dp = with(Context()) {
            px2dp(100)
        }
        assertEquals(100f, dp)
    }
}