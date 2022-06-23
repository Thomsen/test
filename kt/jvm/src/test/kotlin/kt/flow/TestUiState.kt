package kt.flow

import app.cash.turbine.test
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi // for runTest
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

    @Test // if
    fun `collect from shared flow timeout`() = runTest(dispatchTimeoutMs = TIMEOUT_MS) {
        // timeout
//        sharedFlow.emit("Event 1")

        launch {
            sharedFlow.emit("Event 1")
        }

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

    private val flow = flowOf(
        "Event 1",
        "Event 2",
        "Event 3"
    )

    // don't use shareIn or stateIn in a function fun sharedFlow()
    // and use in property var sharedFlow

    @Test
    fun `collect with while subscribed strategy`() = runTest(dispatchTimeoutMs = TIMEOUT_MS) {
        // TestCoroutineScope() can test success TestScope() not
//        val sharingScope = TestCoroutineScope()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val sharingScope = TestScope(dispatcher)

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("Shared Flow COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.WhileSubscribed(),
                1
            )

        // test subscriber is cancelled internally

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    val TIMEOUT_MS = 3_000L

    @Test
    fun `collect with eager strategy old`() = runBlockingTest {
        val sharingScope = TestCoroutineScope()

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
    fun `collect with eager two strategy old`() = runBlockingTest {
        val sharingScope = TestCoroutineScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Eagerly,
                2
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with lazily strategy old`() = runBlockingTest {
        val sharingScope = TestCoroutineScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Lazily,
                1
            )

        // replay count
        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }


    @Test
    fun `collect with lazily two strategy old`() = runBlockingTest {
        val sharingScope = TestCoroutineScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Lazily,
                2
            )

        // replay count
        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with lazily three strategy old`() = runBlockingTest {
        val sharingScope = TestCoroutineScope()

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                sharingScope,
                SharingStarted.Lazily,
                3
            )

        // replay count
        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with eager strategy`() = runTest(dispatchTimeoutMs = TIMEOUT_MS) {
//        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val sharingScope = TestScope(dispatcher)

        // SharingStarted.Eagerly has effect by UnconfinedTestDispatcher

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onEach { println(it) }
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
    fun `collect with eager strategy unconfined`() = runTest(UnconfinedTestDispatcher(), dispatchTimeoutMs = TIMEOUT_MS) {
//        val sharingScope = TestScope()

        // SharingStarted.Eagerly has effect by UnconfinedTestDispatcher
        // eagerly and shareIn scope is TestScope timeout

        val sharedFlow = flow
            .onStart { println("ON START") }
            .onEach { println(it) }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                this,
                SharingStarted.Eagerly,
                1
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with eager strategy standard`() = runTest(StandardTestDispatcher(), dispatchTimeoutMs = TIMEOUT_MS) {
        val sharedFlow = flow
            .onStart { println("ON START") }
            .onEach { println(it) }
            .onCompletion { println("SHARED FLOW COMPLETED") }
            .shareIn(
                this,
                SharingStarted.Eagerly,
                1
            )

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            // standard already is 3
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with lazily strategy`() = runTest(dispatchTimeoutMs = TIMEOUT_MS) {
        // queued up on the underlying scheduler, to be run whenever the test thread is free to use
//        val dispatcher = StandardTestDispatcher(testScheduler)
        // started eagerly on the current thread
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val sharingScope = TestScope(dispatcher)

//        var sharingScope = TestCoroutineScope()

        val sharedFlow = flow
            .shareIn(
                sharingScope,
                SharingStarted.Lazily,
                1
            )
            .onEach { println(it) }
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }

    @Test
    fun `collect with lazily strategy standard`() = runTest(dispatchTimeoutMs = TIMEOUT_MS) {
        val sharedFlow = flow
            .shareIn(
                this,
                SharingStarted.Lazily,
                1
            )
            .onEach { println(it) }
            .onStart { println("ON START") }
            .onCompletion { println("SHARED FLOW COMPLETED") }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 1"
            awaitItem() shouldBeEqualTo "Event 2"
            awaitItem() shouldBeEqualTo "Event 3"
        }

        sharedFlow.test {
            awaitItem() shouldBeEqualTo "Event 3"
        }
    }
}