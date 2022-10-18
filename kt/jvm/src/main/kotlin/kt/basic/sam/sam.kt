package kt.basic.sam

// an anonymous object
val logger0 = object: Consumer<String> {
    override fun consume(a: String?) {
        println(a)
    }
}

// lambda
val logger1 = Consumer<String> { a ->
    println(a)
}


// SAM interfaces supported from kotlin 1.4

// kotlin interface
interface Producer0<A> {
    fun produce(): A
}

val producer0 = object: Producer0<String> {
    override fun produce(): String = "Hel"
}

fun <A> produceNew0(producer: Producer0<A>) = producer.produce()

// sam conversions
fun interface Producer<A> {
    fun produce(): A
    // Fun interfaces must have exactly one abstract method
//    fun print()
}

val producer = Producer { "Hel" }

fun <A> produceNew(producer: Producer<A>) = producer.produce()

interface IntPredicate0 {
    fun accept(i: Int): Boolean
}

val isEven0 = object: IntPredicate0 {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}

fun interface IntPredicate {
    fun calc(i: Int): Boolean
}

val isEven = IntPredicate { it % 2 == 0 }

// diff type alias


