package kt.hard


class CustomerHard {
    private val cart: MutableCollection<Product> = mutableListOf()

    fun addToCart(product: Product) = cart.add(product)

    fun printCart() = println(cart.joinToString { it.name })
}

class Product(var name: String) {
    infix fun and(product: Product): Collection<Product> = listOf(this, product)
}

class Customer {
    private val cart: MutableCollection<Product> = mutableListOf()

    infix fun addToCart(product: Product) = cart.add(product)

    infix fun addToCart(products: Collection<Product>) = cart.addAll(products)

    fun printCart() = println(cart.joinToString { it.name })
}