package kt.basic

import kt.memory.RetainA
import kt.memory.RetainB
import org.junit.Test
import org.openjdk.jol.info.ClassLayout
import org.openjdk.jol.info.GraphLayout

class TestRetain {
    @Test
    fun testA() {
        val a = RetainA()
        println(a.toString())
        println(ClassLayout.parseClass(RetainA::class.java).toPrintable())
        println(GraphLayout.parseInstance(a).toPrintable())
    }

    @Test
    fun testB() {
        val b = RetainB()
        println(ClassLayout.parseClass(RetainB::class.java).toPrintable())
        println(GraphLayout.parseInstance(b).toPrintable())
    }
}