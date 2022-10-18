package kt.generic

// 声明处型变

interface DSource<out T> {
    fun nextT(): T
}

fun ddemo(strs: DSource<String>) {
    val objets: DSource<Any> = strs
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun cdemo(x: kt.generic.Comparable<Number>) {
    x.compareTo(1.0) // Double sub Number
    val y: kt.generic.Comparable<Double> = x
}

fun ddemo2(strs: kt.generic.Comparable<String>) {
    // Type mismatch.
//    val objects: kt.generic.Comparable<Any> = strs
}

fun ddemo3(objs: kt.generic.Comparable<Any>) {
    val strs: kt.generic.Comparable<String> = objs
}