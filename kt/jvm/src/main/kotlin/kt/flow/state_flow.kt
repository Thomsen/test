package kt.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//fun stateFlow() = runBlocking {
//
//}

@OptIn(InternalCoroutinesApi::class)
fun <T> stateFlow(
    scope: CoroutineScope,
    initialValue: T,
    producer: (subscriptionCount: StateFlow<Int>) -> Flow<T>
): StateFlow<T> {
    val state = MutableStateFlow(initialValue)
    scope.launch {
        producer(state.subscriptionCount).collect(state)
    }
    return state.asStateFlow()
}