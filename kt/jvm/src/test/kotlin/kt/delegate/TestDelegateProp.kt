package kt.delegate

import org.junit.Test
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

class TestDelegateProp {

    @Test
    fun testDelegate() {
        val prop = DelegateProp()
        prop.verifiedProp = 66
        assertEquals(5, prop.verifiedProp)

        prop.customDelegated = 550
        assertEquals(5500, prop.customDelegated)
    }

}