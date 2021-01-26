package kt.algo.fibonacci

object Fib {
}

fun Fib.fib(n: Int): Int {
    if (n < 1) return 0
    if (n < 3) return 1

    return Fib.fib(n - 1) + Fib.fib(n - 2)
}

fun Fib.fibPrune(n: Int): Int {
   val cached = mutableMapOf<Int, Int>()
    return if (cached.containsKey(n)) {
        cached[n] ?: 0
    } else {
        val v = Fib.fib(n)
        cached.putIfAbsent(n, v)
        v
    }
}

fun Fib.fibMemorandumFromTopdown(key: Int): Int {

    var index = if (key < 1) {
        0
    } else {
        key
    }
    val size = index + 1
    val router = arrayOfNulls<Int>(size)


    val result =  when {
        index < 1 -> {
            router[index] = 0
            router[index]
        }
        index == 1 -> {
            router[index] = 1
            router[index]
        }
        else -> {
            if (router[index] == null) {
                router[index] = Fib.fibMemorandumFromTopdown(index - 1) + Fib.fibMemorandumFromTopdown(index - 2)
            }
            router[index]
        }
    }

    return result ?: 0
}

fun Fib.fibDpFromDowntop(key: Int): Int {

    var index = if (key < 1) {
        0
    } else {
        key
    }
    val size = index + 1
    val router = arrayOfNulls<Int>(size)

    for (i in 0 until size) {
        if (i < 3) {
            router[i] = if (i < 1) 0 else 1
        } else {
            router[i] = (router[i - 1] ?: 0) + (router[i - 2] ?: 0)
        }
    }

    return router[index] ?: 0
}

fun Fib.fibDpStateCompression(key: Int): Int {
    if (key < 1) return 0
    if (key < 3) return 1

    var prev = 1
    var prePrev = 1
    var cur = 0
    for (i in 3..key) {
        cur = prev + prePrev
        prePrev = prev
        prev = cur
    }
    return cur
}