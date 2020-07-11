package kt.advance

import kt.basic.syntax.plus
import org.junit.Test

class TestInline {

    @Test
    fun sampleFun() {
        // (1)
        val array = arrayListOf<Int>(1, 2, 3)

        // (2
        val plusOne = array.map { it + 1}

        // (3
        println(plusOne)
    }

    @Test
    fun sampleFun2() {
        plusOne(1, 2, 3)
    }

    inline fun plusOne(vararg elements: Int) {
        val array = elements
        val plusOneA = array.map {  it + 1 }
        println(plusOneA)
    }

    @Test
    fun authenticate() {
        val thom = Name("thom")
        println(thom.name)
        println(thom.parsed)
    }
}

inline class Name(val name: String) {
    val parsed: String
        get() = name.toUpperCase().filterIndexed { index, c -> index == 0 }
}
