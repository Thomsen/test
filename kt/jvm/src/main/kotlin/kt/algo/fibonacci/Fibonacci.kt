package kt.algo.fibonacci

class Fibonacci {

}

fun codeRuntime(methods: (() -> Unit)) {
    val start = System.currentTimeMillis()
    methods.invoke()
    val end = System.currentTimeMillis()
    println("Runtime: ${end - start} ms")
}

fun fib(n: Int): Int {
    if (n == 0) return 1
    if (n == 1) return 2
    return fib(n - 1) + fib (n - 2)
}

fun main() {
    println(fib(2))

    codeRuntime {
        println(fib(40))
    }
}

