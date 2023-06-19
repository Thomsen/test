package kt.http

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestHttp {

    @Test
    fun `test http`() {
        val httpClient = HttpExample()
        val response = httpClient.run("https://raw.github.com/square/okhttp/master/README.md")
        assertTrue(response?.isNotEmpty() == true)
    }
}