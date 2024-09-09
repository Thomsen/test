package cast

interface Animal {
    operator fun inc(): Pet = TODO()
}

interface Pet : Animal {
    fun playWithOwner() {
        println("Playing with owner")
    }
}

interface WildAnimal {
    fun roamInWild() {
        println("Roaming in the wild")
    }
}

class Wolf : Animal, WildAnimal {
    override fun inc(): Pet = Dog()
    override fun roamInWild() {
        println("cast.Wolf is roaming in the wild")
    }
}

class Dog : Pet {
    override fun inc(): Pet = this
    override fun playWithOwner() {
        println("cast.Dog is playing fetch with owner")
    }
}

fun main() {
    var creature: Animal = Wolf()

    if (creature is WildAnimal) {
        // inc() operator
        ++creature

        // 2.0.0, creature is smart-cast to cast.Pet
        // 1.9.25 Unresolved reference: playWithOwner
//        creature.playWithOwner()
    }
}
