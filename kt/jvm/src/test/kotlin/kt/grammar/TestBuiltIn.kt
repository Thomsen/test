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
            println("let receiver $this")
            println("let argument $it")
            it.id = 1
            it.name = "testLet"
            it.hobbies = listOf("eat")
        }

        println("let result = $result, user = $user")
        assertEquals(false, result is Unit)
    }

    @Test
    fun testWith() {
        val result = with(user) {
            println("with receiver $this")
            id = 2
            name = "testWith"
            hobbies = listOf("run")
        }
        println("with result = $result, user = $user")
        assertEquals(true, result is Unit)
    }

    @Test
    fun testAlso() {
        val result = user.also {
            println("also receiver $this")
            println("also argument $it")
            it.id = 3
            it.name = "testAlso"
            it.hobbies = listOf("swimming")
        }
        println("also result = $result, user = $user")
        assertEquals(true, result == user)
    }

    @Test
    fun testRun() {
        val result = user.run {
            println("run receiver $this")
            id = 4
            name = "testRun"
            hobbies = listOf("sing")
        }
        println("run result = $result, user = $user")
        assertEquals(true, result is Unit)
    }

    @Test
    fun testApply() {
        val result = user.apply {
            println("apply receiver $this")
            id = 5
            name = "testApply"
            hobbies = listOf("reading")
        }
        println("apply result = $result, user = $user")
        assertEquals(true, result == user)
    }
}