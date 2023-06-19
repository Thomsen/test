package arrow.effect

import org.junit.Test
import kotlin.test.assertTrue

class TestEffect {

    @Test
    fun `test effect main`() {
        val result = TVShowFetcher.fetch("Big Bang")
        println(result)

        assertTrue(result.isNotEmpty())
    }
}