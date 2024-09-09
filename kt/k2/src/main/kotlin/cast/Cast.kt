package cast

class Apple {

    fun foo() {
        println("apple")
    }

}

fun fooProduct(product: Any) {
    val isFruit = product is Apple

    if (isFruit) {
//        product.foo()
    }

}

fun main() {
    val apple = Apple()
    fooProduct(apple)

    val apple2: Any = Apple()
    if (apple2 is Apple) {
        apple2.foo()
    }
}