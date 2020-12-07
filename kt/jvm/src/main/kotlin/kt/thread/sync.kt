package kt.thread

import thread.MuLock
import thread.MuReentrantLock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

fun main() {
    thread(name = "Thread1") {
        val test = Test1(Thread.currentThread().name)
        test.deadLock()
        println("${Thread.currentThread().name} end")
    }

    thread(name = "Thread3") {
        val test = Test1(Thread.currentThread().name)
        test.decrease()
        println("${Thread.currentThread().name} end")
    }

    thread(name = "Thread2") {
        val test = Test2(Thread.currentThread().name)
        test.tryLock()
        println("${Thread.currentThread().name} end")
    }

    thread(name = "Thread4") {
        val test = Test2(Thread.currentThread().name)
        test.decrease()
        println("${Thread.currentThread().name} end")
    }

    thread(name = "Thread5") {
        val test = Test5(Thread.currentThread().name)
        test.tryLock()
        println("${Thread.currentThread().name} end")
    }

    for (i in 6..15) {
        thread(name = "Thread$i") {
            val test = Test6(Thread.currentThread().name)
            test.add()
            println("${Thread.currentThread().name} end")
        }
    }
}

class Test1(private val threadName: String) {
    private val lock = MuLock()

    fun deadLock() {
        lock.lock()
        println("$threadName prepare add")
        add()
        lock.unlock()
    }

    fun add() {
        lock.lock()
        println("$threadName add")
        Thread.sleep(100)
        lock.unlock()
    }

    fun decrease() {
        lock.lock()
        println("$threadName descrease")
        Thread.sleep(100)
        lock.unlock()
    }
}

class Test2(private val threadName: String) {
    private val lock = MuReentrantLock()

    fun tryLock() {
        lock.lock()
        println("$threadName prepare add")
        add()
        lock.unlock()
    }

    fun add() {
        lock.lock()
        println("$threadName add")
        Thread.sleep(100)
        lock.unlock()
    }

    fun decrease() {
        lock.lock()
        println("$threadName descrease")
        Thread.sleep(100)
        lock.unlock()
    }
}

class Test5(private val threadName: String) {
    private val lock = ReentrantLock()

    fun tryLock() {
        lock.lock()
        println("$threadName prepare add")
        add()
        lock.unlock()
    }

    fun add() {
        lock.lock()
        println("$threadName add")
        Thread.sleep(100)
        lock.unlock()
    }

    fun decrease() {
        lock.lock()
        println("$threadName descrease")
        Thread.sleep(100)
        lock.unlock()
    }
}

class Test6(private val threadName: String) {

    var count = 10
    private val lock = ReentrantLock()
    val condition = lock.newCondition()

    fun add() {
        lock.lock()
        try {
            println("$threadName add count = $count")
            while (count % 2 != 0) {
                condition.await()
            }
            count -= 1
            condition.signalAll()
        } finally {
            lock.unlock()
        }
    }

    val lockObj = Object()
    fun addSync() = synchronized(lockObj) {
        while (count % 2 != 0) {
            lockObj.wait()
        }
        lockObj.notifyAll()
    }

}