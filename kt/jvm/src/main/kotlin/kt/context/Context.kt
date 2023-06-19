package kt.context

class Context {
    var density = 0f
}

inline fun Context.px2dp(value: Int): Float = value.toFloat()

class LogContext {
    fun logcat(message: Any) {}
}

class FileContext {
    fun writeFile(message: Any) {}
}

fun printfZero(logContext: LogContext, fileContext: FileContext) {
    with(Context()) {
        val dp = px2dp(100)
        logContext.logcat("print $dp in logcat")
        fileContext.writeFile("write $dp in file")
    }
}

// context receivers freeCompilerArgs = listOf("-Xcontext-receivers")
context(LogContext, FileContext)

fun printfRec() {
    with(LogContext()) {
        with(FileContext()) {
            println("I am DHL")
        }
    }
}

