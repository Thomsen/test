package kt.basic.syntax


fun plus(a: Int, b: Int) = a + b

fun minus(a: Int, b: Int) = a - b

inline fun times(a: Int, b: Int) = a * b

fun div(a: Int, b: Int) = a / b

fun mod(a: Int, b: Int) = a % b

fun main() {
    println(plus(3, 4))

    println(times(3, 4))
}