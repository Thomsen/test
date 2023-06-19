package kt.delegate

import kotlin.reflect.KProperty

class LazyProperty<T>(private val initializer: () -> T) {
    private var value: T? = null

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (value  == null) {
            value = initializer()
        }
        return value ?: initializer()
    }
}
