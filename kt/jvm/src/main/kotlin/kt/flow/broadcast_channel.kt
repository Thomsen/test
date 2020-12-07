package kt.flow

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        val broadcastChannel = BroadcastChannel<String>(Channel.BUFFERED)
        val receiver: ReceiveChannel<String> = broadcastChannel.openSubscription()

        receiver.receive()
        receiver.poll()
        receiver.consumeAsFlow()
    }

}