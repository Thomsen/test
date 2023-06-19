package kt.delegate

import kotlin.reflect.KProperty

class FormatDelegate {

    var formattedString: String = ""

    @OptIn(ExperimentalStdlibApi::class)
    fun format(thisRef: Any, value: String): String {
        if (thisRef is Person) {
            thisRef.updateCount ++
        } else if (thisRef is Student) {
            thisRef.updateCount ++
        }
        return value.lowercase().replaceFirstChar { value.uppercase() }
    }

    fun getter(value: String): String {
        return value + "-" + value.length
    }
}

class Person {
    private val delegate = FormatDelegate()

    var name: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() = delegate.getter(field)

    var updateCount = 0
}

class Student {
    private val delegate = FormatDelegate()

    var name: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }
    var address: String = ""
        set(value) {
            field = delegate.format(this, value)
        }
        get() {
            return delegate.getter(field)
        }

    var updateCount = 0
}

@OptIn(ExperimentalStdlibApi::class)
private operator fun FormatDelegate.setValue(foo: Any, property: KProperty<*>, type: String) {
    formattedString = type.toLowerCase().replaceFirstChar { it.uppercase() }
}

private operator fun FormatDelegate.getValue(foo: Any, property: KProperty<*>): String {
    return formattedString + "-" + formattedString.length
}

class Foo {
    var p: String by FormatDelegate()
}

// new divider line

open class PersonOne (
    var firstName: String,
    var lastName: String,
    var age: Int,
) {
}

// if you add new property for PersonOne, the subclass all modified
open class PersonTwo(
    var firstName: String,
    var lastName: String,
    var age: Int,
    var address: String,
)

class Superhero(
    firstName: String,
    lastName: String,
    age: Int,
    var name: String,
): PersonOne(firstName, lastName, age) {

}

//// Modifier 'open' is incompatible with 'data'
//// one data class cannot inherit from another data class
//open data class PersonOne2 (
//    open var firstName: String,
//    open var lastName: String,
//    open var age: Int,
//) {
//
//}
//data class SuperHero2(
//    override var firstName: String,
//    override var lastName: String,
//    override var age: Int,
//    var name: String,
//): PersonOne2(firstName, lastName, age) {
//
//}

data class PersonThree(
    var firstName: String,
    var lastName: String,
    var age: Int
)

// composition
data class SuperheroTwo(
    private val person: PersonThree,
    var country: String,
) {
    var firstName: String
        get() = person.firstName
        set(value) {
            person.firstName = value
        }

    var lastName: String
        get() = person.lastName
        set(value) {
            person.lastName = value
        }

    var age: Int
        get() = person.age
        set(value) {
            person.age = value
        }
}

// delegation
operator fun <T> PersonThree.setValue(a: Any, property: KProperty<*>, t: T) {
    when(property.name) {
        "firstName" -> this.firstName = a as String
        "lastName" -> this.lastName = a as String
        "age" -> this.age = a as Int
        else -> throw RuntimeException()
    } as T

//    // memberProperties need depend kotlin-reflect
//    val prop = this::class.memberProperties.find { it.name == property.name }
//    if (prop is KMutableProperty<*>) {
//        prop.setter.call(this, a)
//    } else {
//        throw RuntimeException()
//    }
}

// reified need inline
// reified type parameters only work with functions (or extension properties that have a get() function)
inline operator fun <reified T> PersonThree.getValue(a: Any, property: KProperty<*>): T {
    return when(property.name) {
        "firstName" -> this.firstName
        "lastName" -> this.lastName
        "age" -> this.age
        else -> throw RuntimeException()
    } as T

//    // memberProperties need depend kotlin-reflect
//    val prop = this.javaClass.kotlin.memberProperties.find { it.name == property.name }
//    if (prop != null) {
//        return prop.call(this) as T
//    }
//    throw RuntimeException()
}

data class SuperheroThree(
    private val person: PersonThree,
    var country: String,
) {
    var firstName: String by person
    var lastName: String by person
    var age: Int by person
}


