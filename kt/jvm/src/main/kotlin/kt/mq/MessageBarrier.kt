package kt.mq

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class Message(val runnable: Runnable)

class MessageQueue {
    private val queue = LinkedBlockingQueue<Message>()
    private @Volatile var barrier: Boolean = false

    fun next(): Message? {
        if (barrier) return null
        return queue.take()
    }

    fun addMessage(message: Message) {
        queue.put(message)
    }

    fun addBarrier() {
        barrier = true
    }

    fun removeBarrier() {
        barrier = false
    }
}

class Looper(private val queue: MessageQueue) {
    fun loop() {
        while (true) {
            val message = queue.next() ?: continue
            message.runnable.run()
        }
    }
}

fun printlnWithTime(message: Any?) {
    val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
    println("[$timeStamp] $message")
}


fun main() {
    val messageQueue = MessageQueue()
    val looper = Looper(messageQueue)

    Thread {
        looper.loop()
    }.start()

    messageQueue.addMessage(Message(Runnable { printlnWithTime("Message 1") }))
    messageQueue.addBarrier()
    messageQueue.addMessage(Message(Runnable { printlnWithTime("Message 2") }))
    Thread.sleep(4000) // Simulate some work
    messageQueue.removeBarrier()
}
