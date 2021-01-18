package kt.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture

fun fooAsync(s: String): CompletableFuture<Unit> =
    CompletableFuture.supplyAsync {
        print(s)
    }

// hot channels
fun fooProducer(p: Map<String, Any>): ReceiveChannel<Map<String, *>> =
    GlobalScope.produce {
        while (true) {
            send(mapOf<String, Any>())
        }
    }


// cold flows
fun foo(p: Map<String, Any>): Flow<Map<String, *>> =
    flow {
        while (true) {
            emit(mapOf<String, Any>())
        }
    }


fun main() {
    // cold
    runBlocking {
        flow {
            emit(0.1f)
            emit(0.9f)
            emit(1.2f)
        }
            .map { floatNumber ->
                // upstream: the value emitted by the flow
                floatNumber.toInt()
                // downstream: flow of int values
            }
            .catch { println(it) }
            .filter { number ->
                // upstream: flow of int values
                number > 0
                // flow of numbers greater than 0
            }
            .collect {
                print(it)
            }
    }

    // hot
    // channel rendezvous
    val channelRendezvous = Channel<String>(Channel.RENDEZVOUS)
    // buffered (capacity = 2) default 64
    val channelBuffered = Channel<String>(Channel.BUFFERED)
    // unlimited
    val channelUnlimited = Channel<String>(Channel.UNLIMITED)
    // conflated
    val channelConflated = Channel<String>(Channel.CONFLATED)
}