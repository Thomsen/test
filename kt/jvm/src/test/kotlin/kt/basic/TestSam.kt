package kt.basic

import kt.basic.sam.*
import org.amshove.kluent.shouldBe
import org.junit.Test
import kotlin.test.assertEquals

class TestSam {

    @Test
    fun testProducer0() {
        val p = produceNew0(object : Producer0<Int> {
            override fun produce(): Int = 5
        })
        assertEquals(5, p)
    }

    @Test
    fun testProducer() {
        val p = produceNew(Producer { 6 })
        assertEquals(6, p)
    }

    @Test
    fun testEven0() {
        isEven0.accept(7) shouldBe false
    }

    @Test
    fun testEven() {
        isEven.calc(8) shouldBe true
    }
}