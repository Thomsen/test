package kt.advance

import java.io.FileInputStream
import java.util.concurrent.locks.ReentrantLock

typealias IntMapper = (Int) -> Int

class TestTypeAlias {

    fun calculate(param: Int, operation: IntMapper): Int {
        return operation(param)
    }

    inline fun calculateInline(param: Int, operation: IntMapper): Int {
        return operation(param)
    }

    inline fun calculateNoInline(param: Int, noinline operation: IntMapper): Int {
        val o = operation
        param.dec()
        return o(param)
    }

    inline fun <T> Iterable<T>.each(action: (T) -> Unit): Unit {
        for (element:T in this) action(element)
    }

    fun `other standard lib examples`() {
        synchronized(ReentrantLock()) {
            println("do stuff")
            "return_value"
        }

        FileInputStream("path").use {
            println("do other stuff")
        }

        listOf<Int>(1, 2, 3, 4)
            .filter { it < 3 }
            .map { it * 2 }
            .joinToString ()
    }

    fun <T> List<T>.doStuff(ops: List<T>.() -> Unit) {
        ops(this)
        this.ops()
        ops()
    }
}
