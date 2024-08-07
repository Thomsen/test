package kt.collection

import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TestCollections {

    @Test
    fun testIntersect() {
        val l1 = listOf(1, 3, 2, 38, 99)
        val l2 = listOf(2, 4, 12, 45)
        val i = l1.intersect(l2)
        // intersect result [2] is equal listOf(2)
//        assertNotEquals(i, listOf(2))
        assertEquals(i, setOf(2))
        assertEquals(listOf(2), listOf(2))
    }

    @Test
    fun testUnion() {
        val l1 = listOf(1, 3, 2, 38, 99)
        val l2 = listOf(2, 4, 12, 45)
        val i = l1.union(l2)
        assertEquals(setOf(1, 3, 2, 38, 99, 4, 12, 45), i)
        assertEquals(setOf(1, 3, 2, 38, 99, 4, 45, 12), i)
    }

    @Test
    fun testSubtract() {
        val l1 = listOf(1, 3, 2, 38, 99)
        val l2 = listOf(2, 4, 12, 45)
        val i = l1.subtract(l2)
        assertEquals(setOf(1, 3, 38 , 99), i)
    }

    @Test
    fun `test group by`() {
        val words = listOf("a", "abc", "ab", "def", "abcd")
        val byLength = words.groupBy { it.length }

        assertContentEquals(listOf(1,3,2,4), byLength.keys)
    }

}