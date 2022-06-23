package kt.repository

import io.reactivex.rxjava3.annotations.Nullable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.Closeable
import kotlin.coroutines.CoroutineContext


open class ViewModel {

    @Nullable
    private val mBagOfTags: MutableMap<String, Any?> = HashMap()

    private val mCleared = false

    open fun <T> getTag(key: String?): T? {
        if (mBagOfTags == null) {
            return null
        }
        synchronized(mBagOfTags) { return mBagOfTags.get(key) as T }
    }

    open fun <T> setTagIfAbsent(key: String, newValue: T): T {
        var previous: T?
        synchronized(mBagOfTags) {
            previous = mBagOfTags.get(key) as T
            if (previous == null) {
                mBagOfTags.put(key, newValue)
            }
        }
        val result = if (previous == null) newValue else previous!!
        if (mCleared) {
            // It is possible that we'll call close() multiple times on the same object, but
            // Closeable interface requires close method to be idempotent:
            // "if the stream is already closed then invoking this method has no effect." (c)
        }
        return result
    }
}

internal class CloseableCoroutineScope(context: CoroutineContext) : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}

private const val JOB_KEY = "androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY"


public val ViewModel.viewModelScope: CoroutineScope
    get() {
        val scope: CoroutineScope? = this.getTag(JOB_KEY)
        if (scope != null) {
            return scope
        }
        return setTagIfAbsent(
            JOB_KEY,
            CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        )
    }

class HomeViewModel: ViewModel() {
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> get() = _message

    fun loadMessage() {
        viewModelScope.launch {
            _message.value = "Greetings!"
        }
    }
}