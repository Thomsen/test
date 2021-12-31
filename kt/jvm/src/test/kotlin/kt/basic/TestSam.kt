package kt.basic

import kt.basic.sam.Producer
import kt.basic.sam.Producer0
import kt.basic.sam.produceNew
import kt.basic.sam.produceNew0
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
}