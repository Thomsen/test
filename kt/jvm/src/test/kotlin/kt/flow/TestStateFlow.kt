package kt.flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class TestStateFlow {

    // update 1.6 runTest (kotlinx-coroutines-test)
    @Test
    fun `Testing State Flow Basic`() = runTest {
        val _counter = MutableStateFlow(0)
        val counter = _counter.asStateFlow()

        val testResults = mutableListOf<Int>()

        val job = launch {
            counter.toList(testResults)
        }
//        _counter.value = 5
        launch {
            _counter.value = 5
        }

//        runCurrent()
//        advanceTimeBy(1000)
        advanceUntilIdle() // Yields to perform the registrations

        assertEquals(2, testResults.size)
        assertEquals(0, testResults.first())
        assertEquals(5, testResults.last())
        job.cancel()
    }
}