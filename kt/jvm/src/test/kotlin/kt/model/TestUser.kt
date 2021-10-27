package kt.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestUser {

    @Test
    fun testUser() {
        val u1 = User(1, "a", listOf("1", "2"))
        val u2 = User(1, "a", listOf("1", "2"))
        assertNotEquals(u1, u2)
    }

    @Test
    fun testUserData() {
        val u1 = UserData(1, "a", listOf("1", "2"))
        val u2 = UserData(1, "a", listOf("1", "2"))
        assertEquals(u1, u2)
    }

    @Test
    fun testUserDataList() {
        val u1 = UserData(1, "a", listOf("1", "2"))
        val u2 = UserData(1, "a", listOf("1", "2"))
        val ul1 = listOf(u1, u2)
        val ul2 = listOf(u1, u2)
        assertEquals(ul1, ul2)
    }

    @Test
    fun testUserDataMap() {
        val u1 = UserData(1, "a", listOf("1", "2"))
        val u2 = UserData(1, "a", listOf("1", "2"))
        val um1 = hashMapOf<String, UserData>("1" to u1)
        val um2 = hashMapOf<String, UserData>("1" to u2)
        assertEquals(um1, um2)
    }

    @Test
    fun testUser2() {
        val u1 = User2(1, "a", listOf("1", "2"))
        val u2 = User2(1, "a", listOf("1", "2"))
        assertEquals(u1, u2)
    }

    @Test
    fun testUser2List() {
        val u1 = User2(1, "a", listOf("1", "2"))
        val u2 = User2(1, "a", listOf("1", "2"))
        val ul1 = listOf(u1, u2)
        val ul2 = listOf(u1, u2)
        assertEquals(ul1, ul2)
    }

    @Test
    fun testUser2Map() {
        val u1 = User2(1, "a", listOf("1", "2"))
        val u2 = User2(1, "a", listOf("1", "2"))
        val um1 = hashMapOf<String, User2>("1" to u1)
        val um2 = hashMapOf<String, User2>("1" to u2)
        assertEquals(um1, um2)
    }

    @Test
    fun testUser2Map2() {
        val u1 = User2(1, "a", listOf("1", "2"))
        val u2 = User2(1, "a", listOf("1", "2"))
        val um1 = hashMapOf<User2, String>(u1 to "1")
        // use hashCode by put
        um1.put(u2, "2")
        val m1 = um1.get(u1)
        assertEquals(m1, "2")
    }
}