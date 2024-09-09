package standard

import kotlin.enums.enumEntries


enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    // 2.0.0
//    val entries = enumEntries<T>()
//    println("entries $entries") // entries [RED, GREEN, BLUE]
//    println(entries.joinToString { it.name })
    // 1.9.25
//    val values = enumValues<T>()
//    println("values $values}") // values [Lstandard.RGB;@5fa7e7ff}
//    println(values.joinToString { it.name })
}

fun main() {
    // 1.9.25 Unresolved reference: enumEntries
    // 2.0.0 RED, GREEN, BLUE
    printAllValues<RGB>()
}

