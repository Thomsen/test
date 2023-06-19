package kt.basic

fun printFilteredString(list: List<String>, predicate: (String) -> Boolean) {
    list.forEach {
        if (predicate(it)) {
            println(it)
        }
    }
}

fun printFilteredString2(list: List<String>, predicate: ((String) -> Boolean)?) {
    list.forEach {
        if (predicate?.invoke(it) == true) {
            println(it)
        }
    }
}

fun getPrintPredicated(): (String) -> Boolean {
    return { it.startsWith("D") }
}

fun invokeFunc() {
    val list = listOf("Kotlin", "Java", "Dart", "JavaScript")

    printFilteredString(list) {
        it.startsWith("K")
    }

    printFilteredString2(list, null)

    val predicate: (String) -> Boolean = {
        it.startsWith("J")
    }

    printFilteredString2(list, predicate)

    printFilteredString2(list, getPrintPredicated())

    list.filter {
        it.startsWith("K")
    }.map {
        "$it length is ${it.length}"
    }.associate {
        it to it.length
    }.forEach {
        println(it)
        println(it.key)
        println(it.value)
    }

    val java = list.find { it.startsWith("Java") }
    println(java)

    val javascript = list.findLast { it.startsWith("Java") }
    println(javascript)

    val empty = list.findLast { it.startsWith("Jeva") }.orEmpty()
    println(empty)
    
    println("end")
}