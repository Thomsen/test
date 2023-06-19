package kt.inline

import kt.inline.Result
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class TestInlineReified {

    @Test
    fun `test check type`() {
        assertTrue(checkType<String>("test idea"))
        assertFalse(checkType<Int>(11.0))
    }

    @Test
    fun `test convert to object`() {
        val jsonResponse = "{'name': 'thom', 'phone':'+1 xxxx'}"
        val profile = jsonResponse.convertToObject<MyProfile>()
        assertEquals("thom","${profile.name}")
    }

    @Test
    fun `test to my object`() {
        val json = "{\"name\": \"herring\"}"
        val result = json.toMyObject<MyProfile>()
        val mp = MyProfile(name = "herring", phone = null)
        assertEquals(mp, result)
    }

    @Test
    fun `test array exhaustive`() {
        var arr = arrayOf(0, 1, 2, 3, 4, 5)  //,"string value"
        var result2 = arr.average()
        val r = when(result2) {
            is Success -> println("status: succeeded")
            is Failure -> println("status: " + result2.cause?.message)
        }.exhaustive

        assertIs<Success<Int>>(result2)
    }

}