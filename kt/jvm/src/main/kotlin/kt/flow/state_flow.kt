package kt.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val stateFlow = MutableStateFlow(0)

    // observe values
    val job = launch {
        stateFlow.collect {
            println("job1 $it")
        }
    }

    val job2 = launch {
        stateFlow.collect {
            // 0 1 2 3 4 5
            println("job2 $it")
        }
    }

    // change values
    (1..5).forEach { it ->
        delay(500)
        stateFlow.value = it // 1 2 3 4 5
        // value = 1 // 0 1
    }

    // maybe don't print 0 1 2 3 4
    val job3 = launch {
        stateFlow.collect {
            println("job3 $it")
        }
    }

    // wait running job
    job.join()

    job2.join()

    job3.join()
}