package kt.thread

import org.junit.Test

class TestVolatile {

    @Test
    fun `test volatile`() {
        for (i in 1..100) {
            volatile1(i)
        }
        Thread.sleep(3000)
        println("count1 = $count1")
        println("count2 = $count2")
        println("count3 = $count3")
        println("count4 = $count4")
    }
}