package kt.patterns.structural.bridge

interface SandwichInterface {
    fun makeSandwich(filling1: String, filling2: String)
}

abstract class SandwichAbstract {
    lateinit var sandwichInterface: SandwichInterface

    constructor(sandwichInterface: SandwichInterface) {
        this.sandwichInterface = sandwichInterface
    }

    abstract fun make()
}

class Sandwich(
        private val filling1: String,
        private val filling2: String,
        sandwichInterface: SandwichInterface) : SandwichAbstract(sandwichInterface) {

    override fun make() {
        sandwichInterface.makeSandwich(filling1, filling2 )
    }
}

class Open: SandwichInterface {
    override fun makeSandwich(filling1: String, filling2: String) {
        println("open sandwich $filling1 $filling2")
    }
}

class Closed: SandwichInterface {
    override fun makeSandwich(filling1: String, filling2: String) {
        println("closed sandwich $filling1 $filling2")
    }

}