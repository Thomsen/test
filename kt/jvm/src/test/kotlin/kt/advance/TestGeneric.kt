package kt.advance

class TestGeneric {

    fun <T> defaultUpper() {}

    fun <T: Any?> explictUpper() {
    }

    fun <T> genericCollectionFun(list: Collection<T>) {
//        if (list is List<Int>) {
//        }

        if (list is List<*>) {
            // will succeed at runtime
            val unsafe = list as List<Int>
            // can crash at runtime
            val mightCrash:Int = unsafe.last()
        }

    }

    // used for explaining co/contra/invariance
    open class One()
    open class Two(): One()
    class Three(): Two()

    class Both<T> {
        fun both(t: T): T { TODO() }
    }
    class Producer<out T> {
        // <? extends T>
        fun produceT(): T { TODO() }
    }
    class Consumer<in T> {
        // <? super T>
        fun consumeT(t: T) { TODO() }
    }

    // Invariant: if for any two different type parameters A and B,
    // Foo<A> is neither a subtype nor a supertype for Foo<B>
//    val invariant1: Both<Two> = Both<One>()
    val invariant2: Both<Two> = Both<Two>()  // invariant
//    val invariant3: Both<Two> = Both<Three>()

    // Convariant: subtyping is preserved
    // A is a subtype of B, Foo<A> is a subtype of Foo<B>
//    val convariant1: Producer<Two> = Producer<One>()
    val convariant2: Producer<Two> = Producer<Two>()  // covariant
    val convariant3: Producer<Two> = Producer<Three>()

    // Contravarint: subtyping is inverted
    // B is a subtype of A, so Foo<A> is a subtype of Foo<B>
    val contra1: Consumer<Two> = Consumer<One>()
    val contra2: Consumer<Two> = Consumer<Two>()  // contravariant
//    val contra3: Consumer<Two> = Consumer<Three>()

    // out means a generic is covariant and that subtyping is preserved
    // in means a generic is contravariant and that subtyping is inverted

//    val modified: MutableList<Any> = mutableListOf<String>()

    val list: List<Any> = listOf<String>()

}