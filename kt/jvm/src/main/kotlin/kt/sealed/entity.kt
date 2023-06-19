package kt.sealed

import java.util.*

interface IdProvider {
    fun getId(): String
}

class Entity private constructor(val id: String) {
    companion object Factory: IdProvider {

        const val id = "id"

        fun create() = Entity(getId())

        override fun getId(): String {
            return "123"
        }
    }
}

class Entity2(val id : String, val name: String) {
    override fun toString(): String {
        return "id$id name:$name"
    }
}

object EntityFactory {
    fun create() = Entity2("id", "thom")

    fun create2(): Entity2 {
        val id = UUID.randomUUID().toString()
        return Entity2(id, "create2")
    }

    fun create(type: Entity2Type): Entity2 {
        val id = UUID.randomUUID().toString()
        val name = when(type) {
            Entity2Type.EASY -> "easy"
            Entity2Type.MEDIUM -> type.getFormattedName()
            Entity2Type.HARD -> type.name
            else -> "help"
        }
        return Entity2(id, name)
    }

    fun create2(type: Entity2Type): EntitySealed {
        val id = UUID.randomUUID().toString()
        val name = when(type) {
            Entity2Type.EASY -> "easy"
            Entity2Type.MEDIUM -> type.getFormattedName()
            Entity2Type.HARD -> type.name
            else -> "help"
        }
        return when(type) {
            Entity2Type.EASY -> EntitySealed.Easy(id, name)
            Entity2Type.MEDIUM -> EntitySealed.Medium(id, name)
            Entity2Type.HARD -> EntitySealed.Hard(id, name, 2f)
            else -> EntitySealed.Help
        }
    }

//    fun create3(): EntitySealed {
//
//    }

}

enum class Entity2Type {
    EASY, MEDIUM, HARD, T;

    fun getFormattedName() = name.toLowerCase().capitalize()
}

sealed class EntitySealed {
    data class Easy(val id: String, val name: String): EntitySealed()

    data class Medium(val id: String, val name: String): EntitySealed()

    data class Hard(val id: String, val name: String, val multipllier: Float): EntitySealed()

    object Help: EntitySealed() {
        val name = "Help"
    }
}

fun EntitySealed.Medium.printInfo() {
    println("Medium class: $id")
}

val EntitySealed.Medium.info: String
    get() = "some info"

fun printEntity() {
    val entity = Entity.Factory.create()
    Entity.id

    val entity2 = EntityFactory.create()
    print(entity2)

    val entityEasy = EntityFactory.create(Entity2Type.EASY)
    println(entityEasy)

    val entityMedium = EntityFactory.create(Entity2Type.MEDIUM)
    println(entityMedium)

    val entityHard = EntityFactory.create(Entity2Type.HARD)
    println(entityHard)

    val entity2Easy = EntityFactory.create2(Entity2Type.EASY)
    println(entity2Easy)

    val entity2Medium = EntityFactory.create2(Entity2Type.MEDIUM)
    println(entity2Medium)

    val entity2Hard = EntityFactory.create2(Entity2Type.HARD)
    println(entity2Hard)

    val entity2Help = EntityFactory.create2(Entity2Type.T)
    println(entity2Help)

    val entitySealed = entity2Easy

    val msg = when(entitySealed) {
        EntitySealed.Help -> "help class"
        is EntitySealed.Easy -> "easy class"
        is EntitySealed.Medium -> "medium class"
        is EntitySealed.Hard -> "hard class"
        else -> "no class"
    }
    println(msg)

    val entity2Easy2 = EntityFactory.create2(Entity2Type.EASY)

    if (entity2Easy == entity2Easy2) {
        println("they are equal")
    } else {
        println("they are not equal")
    }

    val sealed1 = EntitySealed.Easy("id", "name")
    val sealed2 = EntitySealed.Easy("id", "name")

    if (sealed1 == sealed2) {
        println("they are equal")
    } else {
        println("they are not equal")
    }

    if (sealed1 === sealed2) {
        println("they are equal")
    } else {
        println("they are not equal")
    }

    val sealed3 = sealed1.copy()
    if (sealed1 == sealed3) {
        println("they are equal")
    } else {
        println("they are not equal")
    }

    val sealed4 = sealed1.copy(name = "new name")
    if (sealed1 == sealed4) {
        println("they are equal")
    } else {
        println("they are not equal")
    }

    val medium2 = EntitySealed.Medium("id", "name")
    medium2.printInfo()

}