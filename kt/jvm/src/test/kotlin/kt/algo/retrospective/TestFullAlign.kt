package kt.algo.retrospective

import org.junit.Test

class TestFullAlign {

    @Test
    fun testFullAlign() {
        val fa = FullAlign()
        val result = fa.permute(intArrayOf(1, 2, 3))
        println(result)
    }
}