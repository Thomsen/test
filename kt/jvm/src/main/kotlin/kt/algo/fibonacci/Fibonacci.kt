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
    if (n < 1) return 0
    if (n == 1 || n == 2) return 1
    return fib(n - 1) + fib (n - 2)
}

fun fibVector(n: Int): Int {
    val memo = IntArray(n + 1) { 0 }
    return vectorHelper(memo, n)
}

/**
 * from top down
 */
private fun vectorHelper(memo: IntArray, n: Int): Int {
    if (n < 1) return 0
    if (n < 2) return 1
    if (memo[n] != 0) return memo[n]

    memo[n] = vectorHelper(memo, n - 1) + vectorHelper(memo, n - 2)
    return memo[n]
}

/**
 * bottom up
 */
fun fibDp(n: Int): Int {
    if (n < 1) return 0
    val dp = IntArray(n + 1) { 0 }
    if (n > 0) dp[1] = 1
    if (n > 1) dp[2] = 1
    for (i in 3..n) {
       dp[i] = dp[i - 1] + dp[i - 2]
    }
    return dp[n]
}

fun fibDpReduce(n: Int): Int {
    if (n < 1) return 0
    if (n < 2) return 1
    var prev = 1
    var curr = 1
    for (i in 3..n) {
        val sum = prev + curr
        prev = curr
        curr = sum
    }
    return curr
}