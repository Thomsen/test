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
            print("$it ")
        }
    }

    // change values
    (1..5).forEach { it ->
        delay(500)
        stateFlow.value = it // 0 1 2 3 4
        // value = 1 // 0 1
    }

    // cancel running job
    job.cancel()
    job.join()
}