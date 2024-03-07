package kt.mq

import kt.basic.printlnWithTime
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Predicate

data class Message(val runnable: Runnable, val isAsync: Boolean)

var condition = Predicate { message: Message? ->
    message?.isAsync ?: false
}

class MessageQueue {
    private val queue = LinkedBlockingQueue<Message>()
    private @Volatile var barrier: Boolean = false

    fun next(): Message? {
        // if (barrier) return null
        // return queue.take()

        // Peek at the next message without removing it from the queue
        val message = queue.peek() ?: return null
        // If there's a barrier and the message is synchronous, skip it
        return if (barrier && !message.isAsync) {
            findAsyncMsg()
        } else {
            // If there's no barrier or the message is asynchronous, process it
            queue.take()
        }
    }

    private fun findAsyncMsg(): Message? {
        var foundMessage: Message? = null
        for (msg in queue) {
            if (condition.test(msg)) {
                foundMessage = msg
                break
            }
        }
//        if (!queue.isEmpty()) {
//            val iterator: Iterator<Message> = queue.iterator()
//            do {
//                val currentMessage = iterator.next()
//                if (condition.test(currentMessage)) {
//                    foundMessage = currentMessage
//                    break
//                }
//            } while (iterator.hasNext())
//        }
        if (foundMessage != null) {
            queue.remove(foundMessage)
        }
        return foundMessage
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

private class Looper(private val queue: MessageQueue) {
    fun loop() {
        while (true) {
            val message = queue.next() ?: continue
            message.runnable.run()
        }
    }
}


fun main() {
    val messageQueue = MessageQueue()
    val looper = Looper(messageQueue)

    Thread {
        looper.loop()
    }.start()

    messageQueue.addMessage(Message(Runnable { printlnWithTime("Message 1") }, isAsync = false))
    messageQueue.addBarrier()
    messageQueue.addMessage(Message(Runnable { printlnWithTime("Message 2") }, isAsync = false))
    messageQueue.addMessage(Message(Runnable { printlnWithTime("Message 3") }, isAsync = true))
    Thread.sleep(4000) // Simulate some work
    messageQueue.removeBarrier()
}
