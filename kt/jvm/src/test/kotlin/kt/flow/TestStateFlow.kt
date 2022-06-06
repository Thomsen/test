package kt.flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import kotlin.test.assertEquals

class TestStateFlow {

    // TODO update 1.6 runTest
    @Test
    fun `Testing State Flow Basic`() = runBlocking {
        val _counter = MutableStateFlow(0)
        val counter = _counter.asStateFlow()

        val testResults = mutableListOf<Int>()

        val job = launch {
            counter.toList(testResults)
        }
        _counter.value = 5

        assertEquals(2, testResults.size)
        assertEquals(0, testResults.first())
        assertEquals(5, testResults.last())
        job.cancel()
    }
}