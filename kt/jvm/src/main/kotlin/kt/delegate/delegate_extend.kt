package kt.delegate

interface Base {
    fun print()

    fun printMessage(): String
    fun printMessageLine(): String

    val message: String

}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(message) }

    override fun printMessage(): String {
        print(x)
        return message
    }
    override fun printMessageLine(): String {
        println(x)
        return "$message\n"
    }

    override val message = "BaseImpl: x = $x"

}

class Derived(b: Base) : Base by b {

    override val message = "Message of Derived"

}