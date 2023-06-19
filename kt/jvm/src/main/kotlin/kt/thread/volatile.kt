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
