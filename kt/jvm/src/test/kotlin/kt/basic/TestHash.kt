package kt.basic

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestHash {

    @Test
    fun `test the hashcode collision detected`() {
        val s1 = "Aa"
        val s2 = "BB"

        println("Aa: ${s1.hashCode()}")
        println("BB: ${s2.hashCode()}")

        assertEquals(s1.hashCode(), s2.hashCode())
    }

    @Test
    fun `test the hash collision detected`() {
        val s1 = "Aa"
        val s2 = "BB"

        println("Aa: ${s1.hash()}")
        println("BB: ${s2.hash()}")

        assertNotEquals(s1.hash(), s2.hash())
    }
}