package kt.generic

import com.google.gson.Gson
import kotlin.random.Random

open class Pet(
    open val name: String
)

class Dog(override val name: String): Pet(name) {}


fun <T> chooseFavorite(pets: List<T>): T {
    val favorite = pets[Random.nextInt(pets.size)]
    // the next line won't compile - because `name` can't be resolved
//    println("My favorite pet is ${favorite.name}")
    return favorite
}

// 1. give up on generics for chooseFavorite

// 2. use type parameter constraints
fun <T: Pet> chooseFavorite2(pets: List<T>): T {
    val favorite = pets[Random.nextInt(pets.size)]
    println("My favorite pet is ${favorite.name}")
    return favorite
}

// 形式参数： it's a parameter when you're inside the definition.
fun square(number: Int) = number * number
// number is a parameter

// 实际参数： it's an argument when you're outside the definition.
val area = Math.PI * square(5)
// 5 is an argument to the square() function


// parameters and arguments in generics

class Item<T>(var item: T)
// T is type parameter

val item = Item<String>("title")
// a type argument of String


val json = """{
    "name": "Daval"
}"""

val jet1 = Gson().fromJson<Pet>(json, Pet::class.java)

inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)

val jet2 = Gson().fromJson<Pet>(json)

