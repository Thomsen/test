package kt.flow

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class TestBroadcast {
    @OptIn(ObsoleteCoroutinesApi::class)
    @Test
    fun `test broadcast`() = runTest {
        val channel = BroadcastChannel<String>(Channel.CONFLATED)
        val messages = mutableListOf<String>()

        // Write message to Channel
        launch {
            channel.send("Hello")
            delay(1000)
            channel.send("World")
            delay(1000)
            channel.close()
        }

        // Read message from Channel
        launch {
            channel.consumeEach { message ->
                messages.add(message)
            }
        }

        // waiting
        delay(3000)

        // assert result
        assertEquals(messages, listOf("Hello", "World"))
    }
}