package kt.delegate

import org.junit.Test
import kotlin.test.assertEquals

class TestSetDelegate {

    @Test
    fun testSet1() {
        val s1 = CountingSet1<Int>()
        s1.add(1)
        s1.addAll(listOf(2, 3, 3))
        // not 4 addAll also invoke add
        assertEquals(7, s1.objectAdded)
        assertEquals(3, s1.size)
    }

    @Test
    fun testSet2() {
        val s2 = CountingSet2<Int>()
        s2.add(1)
        s2.addAll(listOf(2, 3, 3))
        assertEquals(4, s2.objectAdded)
        assertEquals(3, s2.size)
    }

    @Test
    fun testSet3() {
        val s3 = CountingSet3<Int>()
        s3.add(1)
        s3.addAll(listOf(2, 3, 3))
        assertEquals(4, s3.objectAdded)
        assertEquals(3, s3.size)
    }
}