package kt.flow

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class TestSharedFlow {

    @Test
    fun `test shared flow`() = runTest {
        val flow = MutableSharedFlow<String>(replay = 1)
        val messages = mutableListOf<String>()

        // send message to Flow
        launch {
            flow.emit("Hello")
            delay(1000)
            flow.emit("World")
            delay(1000)
            flow.emit("Kotlin")
            delay(1000)
            flow.emit("Flow")
            delay(1000)
            flow.emit("Test")
            delay(1000)
            flow.emit("End")
            flow.emit("Final")
            flow.emit("Result")
        }

        // collect Flow message
        launch {
            flow.collect { message ->
                messages.add(message)
            }
        }

        // waiting
        delay(5000)

        // assert except
        assertEquals(
            messages,
            listOf("Hello", "World", "Kotlin", "Flow", "Test")
        )

        // continue waiting
        delay(3000)

        // the latest value "Result"
        assertEquals(flow.first(), "Result")

        coroutineContext.cancelChildren()
        joinAll()
    }
}