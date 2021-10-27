package kt.grammar

import kt.model.User
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
╔══════════╦═════════════════╦═══════════════╦═══════════════╗
║ Function ║ Receiver (this) ║ Argument (it) ║    Result     ║
╠══════════╬═════════════════╬═══════════════╬═══════════════╣
║ let      ║ this@MyClass    ║ String("...") ║ Int(42)       ║
║ run      ║ String("...")   ║ N\A           ║ Int(42)       ║
║ run*     ║ this@MyClass    ║ N\A           ║ Int(42)       ║
║ with*    ║ String("...")   ║ N\A           ║ Int(42)       ║
║ apply    ║ String("...")   ║ N\A           ║ String("...") ║
║ also     ║ this@MyClass    ║ String("...") ║ String("...") ║
╚══════════╩═════════════════╩═══════════════╩═══════════════╝
 */

class TestBuiltIn {

    private lateinit var user: User

    @Before
    fun initialize() {
        user = User()
    }

    @Test
    fun testLet() {
        // let scope function
        val result = user.let {
//            println("let receiver $this")
//            println("let argument $it")
            assertEquals(true, it == user)
            it.id = 1
            it.name = "testLet"
            it.hobbies = listOf("eat")
            "A"
        }

//        println("let result = $result, user = $user")
//        assertEquals(true, result is Unit)
        assertEquals(true, result == "A")
    }

    @Test
    fun testWith() {
        val result = with(user) {
//            println("with receiver $this")
            assertEquals(true, this == user)
            id = 2
            name = "testWith"
            hobbies = listOf("run")
            "A"
        }
//        println("with result = $result, user = $user")
        assertEquals(true, result.equals("A"))
    }

    @Test
    fun testRun() {
        val result = user.run {
//            println("run receiver $this")
            assertEquals(true, this == user)
            id = 4
            name = "testRun"
            hobbies = listOf("sing")
        }
//        println("run result = $result, user = $user")
        assertEquals(true, result is Unit)
    }

    @Test
    fun testAlso() {
        val result = user.also {
//            println("also receiver $this")
//            println("also argument $it")
            assertEquals(true, it == user)
            it.id = 3
            it.name = "testAlso"
            it.hobbies = listOf("swimming")
        }
//        println("also result = $result, user = $user")
        assertEquals(true, result == user)
    }

    @Test
    fun testApply() {
        val result = user.apply {
//            println("apply receiver $this")
            assertEquals(true, this == user)
            id = 5
            name = "testApply"
            hobbies = listOf("reading")
        }
//        println("apply result = $result, user = $user")
        assertEquals(true, result == user)
        assertEquals(true, "testApply" == user.name)
        assertEquals(true, "testApply" == result.name)
    }
}