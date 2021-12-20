package kt.patterns.structrual

import kt.patterns.structural.bridge.Closed
import kt.patterns.structural.bridge.Open
import kt.patterns.structural.bridge.Sandwich
import org.junit.Test

class TestBridge {

    @Test
    fun testSandwichOpen() {
        val openSandwich = Sandwich("cheese", "tomato", Open())
        openSandwich.make()
    }

    @Test
    fun testSandwichClosed() {
        val closedSandwich = Sandwich("ham", "eggs", Closed())
        closedSandwich.make()
    }
}