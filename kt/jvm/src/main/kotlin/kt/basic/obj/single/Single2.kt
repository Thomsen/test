package kt.basic.obj.single

import io.reactivex.rxjava3.core.Single
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class Single2 {

    companion object {
        private var INSTANCE: Single2? = null

        fun getInstance(): Single2 {
            if (INSTANCE == null) {
                try {
                    Thread() {
                        sleep(5000)
                    }.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                INSTANCE = Single2()
            }
            return INSTANCE ?: Single2()
        }
    }

    fun testThread() {
        var single: Array<Int> = Array(size = 100) { 0 }
        for (i in 0..99) {
            val t2 = Thread() {
                single[i] = getInstance().hashCode()
                println("single2 instance: ${single[1]}")
            }
            t2.start()
        }
    }

}

class Single22 private constructor() {
    companion object {
        private var instance: Single22? = null
            get() {
                if (field == null) {
                    field = Single22()
                }
                return field
            }
        // getInstance() Platform declaration clash:
        // The following declarations have the same JVM signature
        fun get(): Single22 {
            return instance!!
        }
    }
}

fun main() {
    val single2 = Single2()
    single2.testThread()
}