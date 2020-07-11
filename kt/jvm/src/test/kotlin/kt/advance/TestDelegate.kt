package kt.advance

import org.junit.Test
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TestDelegate {

    init {
        println("class initialized")
    }

    val lazyOne: Int by lazy {
        println("lazy init")
        (0..1000).random()
    }

    fun usingLazyLocally(computation: () -> String) {
        val lazyLocal: String by lazy {
            computation()
        }
    }

    var observeProp: Int by Delegates.observable(10) { prop: KProperty<*>, old: Int, new: Int ->
        println("changed ${prop.name} from $old to $new ")
    }

    var verifiedProp: Int by Delegates.vetoable(5) { prop: KProperty<*>, old: Int, new: Int ->
        if (new > 10) {
            println("Veto!")
            false
        } else {
            println("No Veto")
            true
        }
    }

    var customDelegated: Int by ModifyingDelegate(100) {
        it * 10
    }

    class ModifyingDelegate<T>(
        initValue: T,
        val modifier: (T) -> T
    ): ReadWriteProperty<Any?, T> {
        private var _current: T = modifier(initValue)

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            println("$thisRef, thank you for delegating '${property.name}' to me!")
            return _current
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            println("$value has been assigned to '${property.name}' in $thisRef. ")
            _current = modifier(value)
        }

    }

    @Test
    fun testDelegate() {
        verifiedProp = 66
        println(verifiedProp)

        customDelegated = 550
        println(customDelegated)
    }

}