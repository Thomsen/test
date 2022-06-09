package kt.flow

import app.cash.turbine.test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.test.assertEquals

class TestUiState {

    private val stateFlow = MutableStateFlow<UIState>(UIState.Success)

    @Test
     fun `test await item`() = runTest {
        flowOf("one", "two").test {
            assertEquals("one", awaitItem())
            assertEquals("two", awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `should emit default value`() = runTest {
        stateFlow.test {
            awaitItem() is UIState.Success
            // Timed out waiting for 60000 ms
//            awaitComplete()
        }
    }

    @Test
    fun `should emit default value complete`() = runTest {
        stateFlow
            .onCompletion {
                println("on complete")
            }
            .test {
                awaitItem() shouldBe UIState.Success
            }
    }

    @Test
    fun `should emit error value`() = runTest {
        // UIState.Error but UIState.Success was expected if MutableStateFlow not <UiState>
        stateFlow.emit(UIState.Error)
        stateFlow
            .test {
                // timeout 60s
//                awaitItem() is UIState.Success
                // failed Expected <kt.flow.UIState$Success@2374d36a>, actual <kt.flow.UIState$Error@54d18072> are not the same instance.
//                awaitItem() shouldBe UIState.Success
                awaitItem() is UIState.Error
            }
    }

    val flowOfEvents = flowOf(
        "Event 1",
        "Event 2"
    )

//    fun <T> Flow<T>.stateIn(
//        scope: CoroutineScope
//    ): StateFlow<T>

    @Test
    fun `convert cold flow to state flow`() = runTest {
        val stateFlow = flowOfEvents.stateIn(this)

        stateFlow.test {
            awaitItem() shouldBeEqualTo "Event 2"
        }

        stateFlow.test {
            awaitItem() shouldBeEqualTo "Event 2"
        }
    }

    val sharedFlow = MutableSharedFlow<String>()

    @Test
    fun `collect from shared flow`() = runTest {
        val job = launch(start = CoroutineStart.LAZY) {
            sharedFlow.emit("Event 1")
        }

        sharedFlow.test {
            job.start()
            awaitItem() shouldBeEqualTo "Event 1"
        }
    }

    @Test
    fun `collect from shared flow timeout`() = runTest {
        sharedFlow.emit("Event 1")

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
        }
    }

    val sharedReplayFlow = MutableSharedFlow<String>(replay = 1)

    @Test
    fun `collect from shared replay flow`() = runTest {
        sharedReplayFlow.emit("Event 1")

        sharedReplayFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
        }
    }

    val flow = flowOf(
        "Event 1",
        "Event 2",
        "Event 3"
    )

    @Test
    fun `collect with while subscribed strategy`() = runTest {
        val sharingScope = TestScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("Shared Flow COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.WhileSubscribed(),
                1
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with eager strategy`() = runTest {
        val sharingScope = TestScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Eagerly,
                1
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with lazily strategy`() = runTest {
        val sharingScope = TestScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Lazily,
                1
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }
}