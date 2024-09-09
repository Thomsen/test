package cast

class Holder(private val provider: (() -> Unit)?) {
    fun process() {
        // 1.9.25
//        provider?.invoke()
        // smart-cast
        if (provider != null) {
            // 2.0.0
//            provider()
        }
    }
}

fun main() {

    val h = Holder {

    }
}