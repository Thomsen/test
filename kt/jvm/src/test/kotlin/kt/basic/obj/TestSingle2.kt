package kt.basic.obj

import kt.basic.obj.single.Single2
import org.junit.Test

class TestSingle2 {

    @Test
    fun `test single 2`() {
        val single2 = Single2()
        single2.testThread()
    }
}