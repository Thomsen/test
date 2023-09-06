package kt.refer

import com.google.gson.Gson
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * deep copy for data class
 */
fun <T : Any> T.deepCopy(): T {
    try {
        if (!this::class.isData) {
            return this
        }

        // need kotlin-reflect support
        return this::class.primaryConstructor!!.let {
                primaryConstructor ->
            primaryConstructor.parameters.map { parameter ->
                val value = (this::class as KClass<T>).memberProperties.first {
                    it.name == parameter.name
                }.get(this)

                if ((parameter.type.classifier as? KClass<*>)?.isData == true) {
                    parameter to value?.deepCopy()
                } else {
                    parameter to value
                }

            }.toMap().let(primaryConstructor::callBy)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return this
    }
}

inline fun <reified T : Any> T.deepGsonCopy(): T {
    try {
        val gson = Gson()
        val content = gson.toJson(this)
        return gson.fromJson(content, T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        return this
    }
}