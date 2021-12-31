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
}

val producer = Producer { "Hel" }

fun <A> produceNew(producer: Producer<A>) = producer.produce()

