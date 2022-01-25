package kt.generic

interface Source<T> {
}

// out 修饰符称为型变注解，并且由于它在类型参数声明处提供，所以我们称之为声明处型变。
// 这与 Java 的使用处型变相反，其类型用途通配符使得类型协变。
interface Source2<out T> {
}

fun demo(strs: Source<String>) {
//    Required: Source<Any> Found: Source<String>
//    val objs: Source<Any> = strs
}

fun demo2(strs: Source2<String>) {
    val objects: Source2<Any> = strs
}


interface Course<T> {
    operator fun compareTo(other: T): Int
}

// in 类型参数逆变：只可以被消费而不可以被生产
interface Course2<in T> {
    operator fun compareTo(other: T): Int
}

fun demo3(x: Course<Number>) {
    x.compareTo(1.0)
//    Required: Course<Double> Found: Course<Number>
//    val y: Course<Double> = x
}

fun demo4(x: Course2<Number>) {
    x.compareTo(1.0)
    val y: Course2<Double> = x
}

//  Producers to objects you only read from (只能从中读取的对象为生产者)
//  Consumers to those you only write to (只能写入的对象为消费者)