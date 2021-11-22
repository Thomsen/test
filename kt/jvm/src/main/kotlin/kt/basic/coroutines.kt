package kt.basic

import kotlinx.coroutines.*


fun main() {
    exampleGlobal()
}

fun exampleGlobal() {
    GlobalScope.launch {
        println("five-0 ${Thread.currentThread()}")
        delay(1000)
        println("five ${Thread.currentThread()}")
    }
    // GlobalScope thread like daemon thread
    // the application is finished, so that it has interrupted
    GlobalScope.launch {
        println("four-0 ${Thread.currentThread()}")
        delay(3000)
        println("four")
    }
    println("one ${Thread.currentThread()}")
    // the blocking coroutine scope for wait execute completed
    runBlocking {
        delay(1000)
        println("two ${Thread.currentThread()}")
    }
    // if not sleep five and four not print
//    Thread.sleep(4000)
    println("three ${Thread.currentThread()}")
}

fun exampleBlocking() = runBlocking {
    println("one-1 ${Thread.currentThread()}")
    println("two-2")
    println("three-3")

    launch {
        println("one ${Thread.currentThread()}")
        delay(1500)
        println("one is end")
    }

    launch {
        println("two ${Thread.currentThread()}")
        delay(1000)
        println("two is end")
    }

    launch3()

    println("four-4 ${Thread.currentThread()}")
}

// launch function to suspend
private suspend fun launch3() {
    println("three ${Thread.currentThread()}")
    delay(2000)
    println("three is end")

    // not coroutine scope environment
}


suspend fun exampleCoroutineScope() = coroutineScope {
    println("one ${Thread.currentThread()}")
    delay(500)

    launch {
        println("two ${Thread.currentThread()}")
        delay(500)
        println ("two is end")
    }

    launch {
        println("three ${Thread.currentThread()}")
        delay(1000)
        println ("three is end")
    }

    println("four ${Thread.currentThread()}")

//    one Thread[main @coroutine#1,5,main]
//    four Thread[main @coroutine#1,5,main]
//    two Thread[main @coroutine#2,5,main]
//    three Thread[main @coroutine#3,5,main]
//    two is end
//    three is end
}

suspend fun exampleCoroutineScope2() = coroutineScope {
    println("one ${Thread.currentThread()}")
    delay(500)

    printDelay("two", 500)

    printDelay("three", 1000)

    println("four ${Thread.currentThread()}")

//    one Thread[main @coroutine#1,5,main]
//    two Thread[main @coroutine#1,5,main]
//    two is end
//    three Thread[main @coroutine#1,5,main]
//    three is end
//    four Thread[main @coroutine#1,5,main]
}

suspend fun printDelay(name: String, time: Long) {
    println("$name ${Thread.currentThread()}")
    delay(time)
    println ("$name is end")
}

fun exampleBlockingAndScope() {
    println("one ${Thread.currentThread()}")
    runBlocking {
        println("two ${Thread.currentThread()}")
        coroutineScope {
            println("three ${Thread.currentThread()}")
            // invoke thread
            launch {
                repeat(5) {
                    printDelay("e $it", 1000)
                }
            }
            println("three end")
        }
    }
    println("one end")

    // [thread name, priority, thread group]
//    two Thread[main @coroutine#1,5,main]
//    three Thread[main @coroutine#1,5,main]

}
