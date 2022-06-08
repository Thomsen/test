package kt.basic.syntax

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson

inline fun <reified T> checkType(param: Any) {
    if (param is T) println("It is the type we are looking for.")
    if (param.javaClass == T::class.java) println("Class matched.")
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

fun main(args: Array<String>) {
    checkType<String>("test idea")
    checkType<Int>(11.0)

    val jsonResponse = "{'name': 'thom', 'phone':'+1 xxxx'}"
    val profile = jsonResponse.convertToObject<MyProfile>()
    println("${profile.name}")

    val json = "{\"name\": \"herring\"}"
    val result = json.toMyObject<MyProfile>()
    println(result)

    var arr = arrayOf(0, 1, 2, 3, 4, 5)  //,"string value"
    var result2 = arr.average()
    when(result2) {
        is Success -> println("status: succeeded")
        is Failure -> println("status: " + result2.cause?.message)
    }.exhaustive

}

data class MyProfile(val name: String, val phone: String?)

