package kt.thread

import kotlin.concurrent.thread

@Volatile
var count1 = 0

@Volatile
var count2 = 0

var count3 = 0

var count4 = 0

fun volatile1(t: Int) {

    var isStop = false

    thread {
        count1 ++
        count3 ++
        println("Thread$t start")
        while (!isStop) {

        }
        count2 ++
        count4 ++
        println("Thread$t end")
    }

    thread {
        isStop = true
    }
}

fun main() {
    for (i in 1..100) {
        volatile1(i)
    }
    Thread.sleep(3000)
    println("count1 = $count1")
    println("count2 = $count2")
    println("count3 = $count3")
    println("count4 = $count4")

}