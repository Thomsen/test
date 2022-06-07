package kt.hard

import org.junit.Test

class TestCustomer {

    @Test
    fun `test customer hard`() {
        val customer = CustomerHard()
        val shoes = Product("shoes")
        val socks = Product("socks")

        customer.addToCart(shoes)
        customer.addToCart(socks)
        customer.printCart()
    }

    @Test
    fun `test customer`() {
        val customer = Customer()
        val shoes = Product("shoes")
        val socks = Product("socks")

        // infix medial expressions
        customer.addToCart(shoes and socks)
        customer.printCart()
    }
}