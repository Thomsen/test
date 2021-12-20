package kt.patterns.structrual

import kt.patterns.structural.adapter.Adapter
import kt.patterns.structural.adapter.BullPowerSupply
import org.junit.Test
import kotlin.test.assertEquals

class TestAdapter {

    @Test
    fun testAdapter() {
        val bull = BullPowerSupply()
        bull.setPositivePole("+")
        bull.setNegativePole("-")
        bull.setGroundPole("0")

        val two = Adapter(bull)

        assertEquals("+", two.getPositivePole())
    }
}