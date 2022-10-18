package kt.generic

// 类型投影

// public class Array<T> {


fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun copy2(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun test1() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }

    // ints type mismatch (require Array<Any)
//    copy(ints, any)

    copy2(ints, any) // from 不仅仅是一个数组，而是一个受限制的（投影的）数组
}