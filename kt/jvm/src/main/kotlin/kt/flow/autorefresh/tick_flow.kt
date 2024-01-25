package kt.flow.autorefresh

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kt.flow.stateFlow
import java.time.LocalDateTime
import kotlin.coroutines.CoroutineContext
import kotlin.time.*
import kotlin.time.Duration.Companion.milliseconds


@OptIn(ExperimentalTime::class)
fun tickerFlow(period: Duration): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(period)
    }
}

fun tickerFlow(period: Long): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(period)
    }
}


@OptIn(ExperimentalTime::class)
object ElapsedRealTimeSource : AbstractLongTimeSource(DurationUnit.NANOSECONDS) {
//    override fun read(): Long = SystemClock.elapsedRealtimeNanos()

    override fun read(): Long = System.currentTimeMillis()

    override fun toString(): String = "TimeSource(SystemClock.elapsedRealtimeNanos())"
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.flowWhileShared(
    subscriptionCount: StateFlow<Int>,
    started: SharingStarted
): Flow<T> {
    return started.command(subscriptionCount)
        .distinctUntilChanged()
        .flatMapLatest {
            when (it) {
                SharingCommand.START -> this
                SharingCommand.STOP,
                SharingCommand.STOP_AND_RESET_REPLAY_CACHE -> emptyFlow()
            }
        }
}

@OptIn(InternalCoroutinesApi::class, ExperimentalTime::class)
fun synchronizedTickerFlow(
    millis: Long,
    subscriptionCount: StateFlow<Int>,
    timeSource: TimeSource = ElapsedRealTimeSource
): Flow<Unit> {
    return flow {
        var nextEmissionTimeMark: TimeMark? = null
        flow {
            nextEmissionTimeMark?.let { delay(-it.elapsedNow()) }
            while (true) {
                emit(Unit)
                nextEmissionTimeMark = timeSource.markNow() + millis.milliseconds
                delay(millis)
            }
        }
            .flowWhileShared(subscriptionCount, SharingStarted.WhileSubscribed())
            .collect(this)
    }
}

class CustomCoroutineScope : CoroutineScope {
    private val job = Job()

//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.Main + job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Unconfined + job

    fun cancel() {
        job.cancel()
    }
}

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
val results: StateFlow<String> = stateFlow(CustomCoroutineScope(), "1000") { subscriptionCount ->
    synchronizedTickerFlow(1000, subscriptionCount)
        .mapLatest {
            "load data ${LocalDateTime.now()}"
        }
}

fun main() {
    runBlocking {
        results.collectLatest {
            println("rs $it")
        }
    }
}