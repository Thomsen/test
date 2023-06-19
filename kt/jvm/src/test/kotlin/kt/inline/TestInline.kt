package kt.inline

import org.junit.Test
import kotlin.test.*

class TestInline {

    @Test
    fun `test plus 3 + 4`() {
        assertEquals(7, plus(3, 4))
    }

    @Test
    fun `test times 3 * 4`() {
        //    byte a$iv = 3;
        //    int b$iv = 4;
        //    int $i$f$times = false;
        //    var0 = a$iv * b$iv;
        assertEquals(12, times(3, 4))
    }

    @Test
    fun `test abs is a`() {
        //    Object value$iv = "abc";
        //    $i$f$isA = false;
        //    boolean var6 = true;
        //    $i$f$isA = false;
        //    System.out.println(var6);
        assertTrue(isA<String>("abc"))
        assertFalse(isA<String>(123))
    }

    @Test
    fun `test name pretty`() {
        //    (new Name("t")).prettyPrint();
        assertEquals("let's t!", Name("t").prettyPrint())
    }

    @Test
    fun `test name v pretty`() {
        //    NameV.prettyPrint-impl(NameV.constructor-impl("tt"));
        assertEquals("let's ttt!", NameV("ttt").prettyPrint())
    }

    @Test
    fun sampleFun() {
        // (1)
        val array = arrayListOf<Int>(1, 2, 3)

        // (2
        val plusOne = array.map { it + 1}

        // (3
        assertContentEquals(listOf(2, 3, 4), plusOne)
    }

    @Test
    fun sampleFun2() {
        plusOne(1, 2, 3)
    }

    @Test
    fun authenticate() {
        val thom = NameN("thom")
        println(thom.name)
        println(thom.parsed)
    }
}
