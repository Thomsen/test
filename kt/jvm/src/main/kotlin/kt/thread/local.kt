package kt.thread


class ThreadLocalDemo {
    companion object {
        val threadLocalValue: ThreadLocal<Int> = ThreadLocal.withInitial { 1 }
    }
}

fun main() {
    // Thread A: Accesses and modifies the ThreadLocal value
    Thread {
        println("Thread A initial value: ${ThreadLocalDemo.threadLocalValue.get()}")
        ThreadLocalDemo.threadLocalValue.set(10)
        println("Thread A new value: ${ThreadLocalDemo.threadLocalValue.get()}")
    }.start()

    // Thread B: Accesses and modifies the ThreadLocal value
    Thread {
        println("Thread B initial value: ${ThreadLocalDemo.threadLocalValue.get()}")
        ThreadLocalDemo.threadLocalValue.set(20)
        println("Thread B new value: ${ThreadLocalDemo.threadLocalValue.get()}")
    }.start()
}