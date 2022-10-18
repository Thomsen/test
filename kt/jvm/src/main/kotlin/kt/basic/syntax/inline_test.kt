package kt.basic.syntax



fun plus(a: Int, b: Int) = a + b
//public static final int plus(int a, int b) {
//  return a + b;
//}

fun minus(a: Int, b: Int) = a - b

// 1.5+ @JvmInline not the method call
inline fun times(a: Int, b: Int) = a * b
//public static final int times(int a, int b) {
//    int $i$f$times = 0;
//    return a * b;
//}

fun div(a: Int, b: Int) = a / b

fun mod(a: Int, b: Int) = a % b

// Cannot check for instance of erased type: T
//fun <T> isS(value: Any) = value is T

// reified is don't type erasure
inline fun <reified T> isA(value: Any) = value is T
//// $FF: synthetic method
//public static final boolean isA(Object value) {
//    int $i$f$isA = 0;
//    Intrinsics.checkNotNullParameter(value, "value");
//    Intrinsics.reifiedOperationMarker(3, "T");
//    return value instanceof Object;
//}

interface Printable {
    fun prettyPrint(): String
}

// Value classes without @JvmInline annotation are not supported yet
// value class Name(val n: String): Printable {
class Name(val n: String): Printable {
    override fun prettyPrint(): String {
        val it = "let's $n!"
        println(it)
        return it
    }
}

@JvmInline
value class NameV(val n: String): Printable {
    override fun prettyPrint(): String {
        val it = "let's $n!"
        println(it)
        return it
    }
}

typealias NameTypeAlias = String
@JvmInline
value class NameInlineClass(val s: String)
// 内联类引入了一个真实的新类型
// 类型别名仅仅是为现有的类型取了个新的替代名称

fun main() {
    println(plus(3, 4))
//    int var0 = plus(3, 4);

    println(times(3, 4))
//    byte a$iv = 3;
//    int b$iv = 4;
//    int $i$f$times = false;
//    var0 = a$iv * b$iv;

    println(isA<String>("abc"))  		// true
    println(isA<String>(123)) 		// false
//    Object value$iv = "abc";
//    $i$f$isA = false;
//    boolean var6 = true;
//    $i$f$isA = false;
//    System.out.println(var6);

    Name("t").prettyPrint()
    NameV("tt").prettyPrint()
//    (new Name("t")).prettyPrint();
//    NameV.prettyPrint-impl(NameV.constructor-impl("tt"));

}

// noinline
//inline fun higherOrderFunction(aLambda: () -> Unit, noinline dontInlineLambda: () -> Unit, aLambda2: () -> Unit) {
//
//    doSomething()
//
//    aLambda()
//    dontInlineLambda()//won't be inlined.
//    aLambda2()
//
//    doAnotherThing()
//
//}

// crossinline
//inline fun higherOrderFunction(crossinline aLambda: () -> Unit) {
//
//    normalFunction {
//        aLambda() //must mark aLambda as crossinline to use in this context.
//    }
//
//}
//
//fun normalFunction(aLambda: () -> Unit) {
//
//    return
//
//}