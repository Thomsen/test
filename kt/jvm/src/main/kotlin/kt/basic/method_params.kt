package kt.basic

fun say() {
    println("Hello World")
}

fun people(hello: () -> Unit) {
    hello()
}

fun son(hello: (() -> Unit)?) {
    hello?.run {
        this()
    }
}

fun main() {
    people ({ say() })
    people { say() }

    son { say() }
}
