package kt.advance

import org.junit.Test


class TestType {

    @Test
    // fast blast nothing type rundown
    fun notImplemented(): List<List<List<String>>> {
        TODO()
    }

    @Test
    fun unreachable(someNullable: String?) {
        val nonNull: String = someNullable ?: throw NotImplementedError()
        nonNull.last()

        // Nothing type indicates
        // the function never completes.
        // That's why the assignment is unreachable
        val thing: Nothing = TODO()
    }

    // Any type vs object
    class Something
    class Something2: Any()

    fun primitiveNiceties() {
        val pseudoPrimitive: Int = 1
        // kotlin "primitives" are all considered subtypes of Any
        val anyField: Any = pseudoPrimitive
    }

    fun any() {
        val value: Any = Any()
        value.hashCode()
        value.equals("other")
        value.toString()

        // still have access to the object, but it's a warning
        (value as Object).notify()

        val any3: Any? = 2
        // but the reverse doesn't work
//        val any4: Any = any3
    }

    fun defaultReturn(): Unit {}

    val thing: Unit = Unit

    interface ResultOrError<out R: Any, out E: Exception> {
        // some apis return boolean == success
        // some api are side effects who's proper result type would be Unit
    }

    class ExampleResult: ResultOrError<Unit, UninitializedPropertyAccessException>
}