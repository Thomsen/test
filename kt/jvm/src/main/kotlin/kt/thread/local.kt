package kt.thread

import kt.basic.printlnWithTime


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


    val threadLocal: ThreadLocal<String> = InheritableThreadLocal()
    threadLocal.set("the value of parent")

    var parentValue = "the direct value of parent";
    val localData = LocalData<String>()
    localData.set("the local value of parent")

    Thread {
        printlnWithTime("the child thread print: ${threadLocal.get()}")
        printlnWithTime("the child thread print(direct): $parentValue")
        printlnWithTime("the child thread print(local): ${localData.get()}")

        threadLocal.set("the value of child")
        parentValue = "the direct value of child"
        localData.set("the local value of child")

        printlnWithTime("the child thread print(after): ${threadLocal.get()}")


    }.start()

    Thread {
        Thread.sleep(1000)
        printlnWithTime("the child thread print: ${threadLocal.get()}")
        printlnWithTime("the child thread print(direct): $parentValue")
        printlnWithTime("the child thread print(local): ${localData.get()}")

    }.start()

}