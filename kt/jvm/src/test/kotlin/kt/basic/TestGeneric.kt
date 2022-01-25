package kt.basic

import generic.GenericExtend
import org.junit.Test

class TestGeneric {

    @Test
    fun testJavaGenericExtend() {
        val ge = GenericExtend()
        ge.copyAll(listOf("to"), listOf("from"))
    }
}