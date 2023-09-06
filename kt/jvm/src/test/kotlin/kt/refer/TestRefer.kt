package kt.refer

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TestRefer {

    @Test
    fun testRefer1() {
        var ro1: ReferObject?= ReferObject("refer")
        var ro2 = ro1
        ro1 = null
        assertEquals(ro2?.name, "refer")
    }

    @Test
    fun testRefer2() {
        var ro1: ReferObject?= ReferObject("refer")
        var ro2 = ro1
        ro1?.name = null
        assertEquals(ro2?.name, null)
    }

    @Test
    fun testReferData1() {
        var ro1: ReferDataObject?= ReferDataObject("refer")
        var ro2 = ro1
        ro1 = null
        assertEquals(ro2?.name, "refer")
        // ro1 is null， but the name has also point， so name don't gc
        assertNull(ro1)
    }

    @Test
    fun testReferData2() {
        var ro1: ReferDataObject?= ReferDataObject("refer")
        var ro2 = ro1
        ro1?.name = null
        assertEquals(ro2?.name, null)
    }

    @Test
    fun testReferDeep() {
        var ro1: ReferObject?= ReferObject("refer")
        var ro2 = ro1?.deepCopy()
        ro1?.name = null
        assertEquals(ro2?.name, null)
    }

    @Test
    fun testReferDataDeep() {
        var ro1: ReferDataObject?= ReferDataObject("refer")
        var ro2 = ro1?.deepCopy()
        ro1?.name = null
        assertEquals(ro2?.name, "refer")
    }

    @Test
    fun testReferDataGsonDeep() {
        var ro1: ReferObject?= ReferObject("refer")
        var ro2 = ro1?.deepGsonCopy()
        ro1?.name = null
        assertEquals(ro2?.name, "refer")
    }
}