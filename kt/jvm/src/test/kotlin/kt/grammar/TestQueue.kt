package kt.grammar

import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class TestQueue {

    lateinit var nums: Queue<Int>

    @Before
    fun setupInit() {
        nums = LinkedList<Int>(listOf(1, 2, 3, 4))
    }

    @Test
    fun testInit() {
        assertEquals(listOf(1, 2, 3, 4), nums.toList())
    }

    @Test
    fun testAdd() {
        nums.add(5)
        assertEquals(listOf(1, 2, 3, 4, 5), nums.toList())
    }

    @Test
    fun testRemove() {
        assertEquals(1, nums.remove())
        assertEquals(listOf(2, 3, 4), nums.toList())
    }

    @Test
    fun testPoll() {
        assertEquals(1, nums.poll())
        assertEquals(listOf(2, 3, 4), nums.toList())
    }

    @Test
    fun testPeek() {
        assertEquals(1, nums.peek())
        assertEquals(listOf(1, 2, 3, 4), nums.toList())
    }

    @Test
    fun testOffer() {
        assertEquals(true, nums.offer(5))
        assertEquals(listOf(1, 2, 3, 4, 5), nums.toList())
    }


}