package kt.advance

import org.junit.Test
import java.lang.IndexOutOfBoundsException


data class NumberHolder(val a: Int, val b: Int): Comparable<NumberHolder> {

    override fun compareTo(other: NumberHolder): Int {
        return (a + b).compareTo(other.a + other.b)
    }

}

operator fun NumberHolder.plus(other: NumberHolder): NumberHolder {
    return NumberHolder(a + other.a, b + other.b)
}

fun NumberHolder.plusX(other: NumberHolder): NumberHolder {
    return NumberHolder(a + other.a, b + other.b)
}

operator fun NumberHolder.inc(): NumberHolder {
    return NumberHolder(a + 1, b + 1)
}

operator fun NumberHolder.get(i: Int): Int {
    return when(i) {
        0 -> a
        1 -> b
        else -> throw IndexOutOfBoundsException()
    }
}

operator fun NumberHolder.invoke() {

}

class TestOperator {
    @Test
    fun testNumberHolder() {
        val added = NumberHolder(1, 2) + NumberHolder(4, 4)
        println(added)
    }
}