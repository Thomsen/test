package kt.algo.reverse

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.assertEquals

class TestReverseOrderPair {

    lateinit var rop: ReverseOrderPair

    lateinit var arrayInt: IntArray

    // @BeforeClass should be static
    @Before
    fun setUp() {
        rop = ReverseOrderPair()
        arrayInt = intArrayOf(5, 1, 4, 2, 8)
    }

    @Test
    fun testReversePairs() {
        assertEquals(4, rop.reversePairs(arrayInt))
    }

    @Test
    fun testReverseMergePairs() {
        assertEquals(4, rop.reverseMergePairs(arrayInt))

        assertEquals(10, rop.reverseMergePairs(intArrayOf(8, 5, 4, 2, 1)))
    }
}