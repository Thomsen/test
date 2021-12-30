package kt.patterns.behavioral

import org.junit.Test
import kotlin.test.assertEquals

class TestStrategy {

    @Test
    fun testCash() {
        val payment = Payment(Cash())
        assertEquals("4.00", payment.employStrategy(4.00f))
    }

    @Test
    fun testCard() {
        val payment = Payment(Card())
        assertEquals("4.25", payment.employStrategy(4.00f))
    }

    @Test
    fun testCoupon() {
        val payment = Payment(Coupon())
        assertEquals("3.60", payment.employStrategy(4.00f))
    }
}