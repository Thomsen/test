
fun main(args: Array<String>) {

    var open = listOf(1, 2, 3, 4).filter {
        it == 2
    }
    println(open.isEmpty())

    var open1 = listOf(1, 2, 3, 4).filter {
        it == 10
    }
    println(open1.isEmpty())
}