package kt.basic

import kt.basic.syntax.BaseImpl
import kt.basic.syntax.Derived
import org.junit.Test

class TestDelegate {

    @Test
    fun testDerived() {
        val b = BaseImpl(10)
        val derived = Derived(b)
        derived.print()
        derived.printMessageLine()

        println(derived.message)
    }

}