import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

public class BasicSyntaxTest {

    @Test
    fun testVariableDeclaration() {
        val immutable = 42
        var mutable = 10

        assertEquals(42, immutable)
        assertEquals(10, mutable)

        mutable = 20
        assertEquals(20, mutable)
    }

    @Test
    fun testStringTemplates() {
        val name = "Kotlin"
        val greeting = "Hello, $name!"

        assertEquals("Hello, Kotlin!", greeting)
    }

    @Test
    fun testNullSafety() {
        var nullableString: String? = "Not null"
        assertNotNull(nullableString)

        nullableString = null
        assertNull(nullableString)

        val length = nullableString?.length ?: -1
        assertEquals(-1, length)
    }

    @Test
    fun testWhenExpression() {
        fun describe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }

        assertEquals("One", describe(1))
        assertEquals("Greeting", describe("Hello"))
        assertEquals("Long", describe(42L))
        assertEquals("Not a string", describe(3.14))
        assertEquals("Unknown", describe("Kotlin"))
    }

    @Test
    fun testRanges() {
        val inRange = 5 in 1..10
        assertTrue(inRange)

        val notInRange = 15 !in 1..10
        assertTrue(notInRange)
    }
}