package kt.repository

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean

class BetterRepository(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(ioDispatcher)

    val initialized = AtomicBoolean(false)

    fun initialize() = scope.async {
        initialized.set(true)
    }

    // A suspending function that switches to the IO dispatcher
    suspend fun fetchData(): String = withContext(ioDispatcher) {
        require(initialized.get()) { "Repository should be initialized first" }
        delay(500L)
        "Hello world"
    }
}
