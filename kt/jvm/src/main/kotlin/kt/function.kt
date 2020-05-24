import java.util.*

class User {
    var id: Int = 0
    var name: String? = null
    var hobbies: List<String>? = null

    override fun toString(): String {
        return "User(id=$id, name=$name, hobbies=$hobbies)"
    }
}

fun main(args: Array<String>) {
    val user = User()
    user.id = 1
    user.name = "test1"
    user.hobbies = listOf("aa", "bb", "cc")
    println("user = $user")

    user.let {
        it.id = 2
        it.name = "test2"
        it.hobbies = listOf("aa", "bb", "cc")
    }
    println("user = $user")

    user.also {
        it.id = 3
        it.name = "test3"
        it.hobbies = listOf("aa", "bb", "cc")
    }
    println("user = $user")

    user.apply {
        id = 4
        name = "test4"
        hobbies = listOf("aa", "bb", "cc")
        Date()
    }
    println("user = $user")

    user.run {
        id = 5
        name = "test5"
        hobbies = listOf("aa", "bb", "cc")
        Date()
    }
    println("user = $user")

    with(user) {
        id = 6
        name = "test6"
        hobbies = listOf("aa", "bb", "cc")
        Date()
    }
    println("user = $user")


    val str: String = "..."
    val result = str.let {
        //print(this) // Receiver
        print(it) // Argument
        42 // Block return value
    }
    println(result)

    val mc = MyClass()
    mc.test()

    val result2 = str.apply {
        print(this) // Receiver
        // print(it) // Argument
        42 // Block return value
    }
    println(result2)

}

class MyClass {
    fun test() {
        val str: String = "..."
        val result = str.let {
            print(this) // Receiver
            print(it) // Argument
            42 // Block return value
        }
        println(result)
    }
}

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
