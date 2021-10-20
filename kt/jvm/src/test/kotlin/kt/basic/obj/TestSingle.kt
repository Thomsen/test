package kt.basic.obj

import kt.basic.obj.single.Single1
import kt.basic.obj.single.Single2
import kt.basic.obj.single.Single22
import org.junit.Test
import thread.JSingle2
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread
import kotlin.test.assertEquals

class TestSingle {
    @Test
    fun testSingle1() {
        val o1 = Single1.getInstance()
        val o2 = Single1.getInstance()
        assertEquals(o1, o2)
        assert(o1 == o2)
    }

    @Test
    fun testSingle1_1() {
        val o1 = Single1.getInstance()
        val o2 = Single1()
        assert(o1 != o2)
    }

    @Test
    fun testSingle2() {
        val o1 = Single2.getInstance()
        val o2 = Single2.getInstance()
        assertEquals(o1, o2)
        assert(o1 == o2)
    }

    @Test
    fun testSingle22() {
        val o1 = Single22.get()
        val o2 = Single22.get()
        assertEquals(o1, o2)
        assert(o1 == o2)
    }

    @Test
    fun testSingle2_1() {
        val countDownLatch = CountDownLatch(100)
        var single: Array<Int> = Array(size = 100) { 0 }
        for (i in 0..99) {
            val t = thread {
                single[i] = Single2.getInstance().hashCode()
                println("single2 instance: ${single[1]}")
                if (i > 1) {
                    assertEquals(single[i], single[i-1])
                }
                countDownLatch.countDown()
            }
            t.join()
        }
        try {
            countDownLatch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testJSingle2_1() {
        val countDownLatch = CountDownLatch(100)
        var single: Array<Int> = Array(size = 100) { 0 }
        for (i in 0..99) {
            val t = thread {
                single[i] = JSingle2.getInstance().hashCode()
                println("java single2 instance: ${single[1]}")
                if (i > 1) {
                    assertEquals(single[i], single[i-1])
                }
                countDownLatch.countDown()
            }
            t.join()
        }
        try {
            countDownLatch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}