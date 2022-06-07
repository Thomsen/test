package kt.hard

import java.math.BigDecimal

class MoneyHard(value: Long) {
    private var balance = BigDecimal.valueOf(value)
    override fun toString(): String = "$${balance.setScale(2)}"

    fun add(value: BigDecimal) {
        balance = balance.add(value)
    }
}

// suggest

typealias `$` = Money

class Money(value: Long) {
    private var balance = BigDecimal(value)

    operator fun plusAssign(value: Money) {
        balance = balance.add(value.balance)
    }

    override fun toString(): String = "$${balance.setScale(2)}"

    fun `can I buy the new Apple M2 computer`() = println("NO")
}