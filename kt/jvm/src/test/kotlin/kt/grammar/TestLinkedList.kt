package kt.grammar

import kt.parser.num
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception
import java.util.*
import kotlin.NoSuchElementException
import kotlin.test.assertEquals

class TestLinkedList {

    lateinit var nums: LinkedList<Int>

    @Before
    fun setupInit() {
        nums = LinkedList<Int>(listOf(1, 2, 3, 4))
    }

    @Test
    fun testInit() {
        assertEquals(listOf(1, 2, 3, 4), nums)
    }

    @Test
    fun testAdd() {
        nums.add(5)
        assertEquals(listOf(1, 2, 3, 4, 5), nums)
    }

    @Test
    fun testRemove() {
        assertEquals(1, nums.remove())
        assertEquals(listOf(2, 3, 4), nums)
    }

    @Test
    fun testPoll() {
        assertEquals(1, nums.poll())
        assertEquals(listOf(2, 3, 4), nums)

        assertEquals(null, LinkedList<Int>().poll())
    }

    @Test
    fun testPeek() {
        assertEquals(1, nums.peek())
        assertEquals(listOf(1, 2, 3, 4), nums)
    }

    @Test
    fun testOffer() {
        assertEquals(true, nums.offer(5))
        assertEquals(listOf(1, 2, 3, 4, 5), nums)
    }

    @Test
    fun testPop() {
        assertEquals(1, nums.pop())
        assertEquals(listOf(2, 3, 4), nums)
    }

    @Test(expected = NoSuchElementException::class)
    fun testPopException() {
        // NoSuchElementException
        try {
            LinkedList<Int>().pop()
        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun testPush() {
        assertEquals(Unit, nums.push(5))
        assertEquals(listOf(5, 1, 2, 3, 4), nums)
    }

}