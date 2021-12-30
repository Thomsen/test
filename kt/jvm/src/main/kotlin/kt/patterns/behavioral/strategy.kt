package kt.patterns.behavioral

interface Strategy {
    fun processPayment(price: Float): String
}

class Cash: Strategy {
    override fun processPayment(price: Float): String {
        return String.format("%.2f", price)
    }
}

class Card: Strategy {
    override fun processPayment(price: Float): String {
        return String.format("%.2f", price + 0.25f)
    }
}

class Coupon: Strategy {
    override fun processPayment(price: Float): String {
        return String.format("%.2f", price * 0.9f)
    }
}

class Payment(private val strategy: Strategy) {
    fun employStrategy(f: Float): String {
        return strategy.processPayment(f)
    }
}

