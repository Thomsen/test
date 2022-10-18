package kt.basic.syntax

interface Base {
    fun print()

    fun printMessage()
    fun printMessageLine()

    val message: String

}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(message) }

    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }

    override val message = "BaseImpl: x = $x"

}

class Derived(b: Base) : Base by b {

    override val message = "Message of Derived"

}