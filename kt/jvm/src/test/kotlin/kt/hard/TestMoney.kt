package kt.hard

import org.junit.Test
import java.math.BigDecimal

class TestMoney {
    @Test
    fun testMoneyHard1() {
        val money = MoneyHard(10)
        println(money)
    }

    @Test
    fun `test money hard add`() {
        val money = MoneyHard(10)
        money.add(BigDecimal.valueOf(20))
        println(money)
    }

    @Test
    fun `test money add`() {
        val money = `$`(10)
        money += `$`(20)
        println(money.`can I buy the new Apple M2 computer`())
    }
}