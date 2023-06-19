package kt.inline

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson

inline fun <reified T> checkType(param: Any): Boolean {
    if (param is T) println("It is the type we are looking for.")
    return if (param.javaClass == T::class.java) {
        println("Class matched.")
        true
    } else {
        false
    }
}


inline fun <reified T:Any> String.convertToObject(): T{
    val gson = Gson()
    return gson.fromJson(this,T::class.java)
}

inline fun <reified T: Any> String.toMyObject(): T {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(this, T::class.java)
}

inline fun <reified T> List<Any>.filterFruit(): List<T> {
    return this.filter { it is T }.map { it as T }
}
inline fun <reified T> Any.isInstanceOf(): Boolean = this is T

inline fun <reified T> Array<T>.average() : Result<Float> {
    return if (T::class.java == Integer::class.java){
        var sum = 0
        for (item in this){
            if (item is Int) sum += item
        }
        val result = (sum / this.size).toFloat()
        println("result: $result")
        Success(result)
    } else {
        Failure(IllegalArgumentException("$this does not conform to the number."))
    }
}
sealed class Result<out T: Any>
class Success<out T: Any>(val result: T) : Result<T>()
class Failure(val cause: Exception? = null) : Result<Nothing>()
val <T> T.exhaustive: T
    get() = this

data class MyProfile(val name: String, val phone: String?)

