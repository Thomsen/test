
fun main(args: Array<String>) {

    val words = listOf("a", "abc", "ab", "def", "abcd")
    val byLength = words.groupBy { it.length }

    println(byLength.keys) // [1, 3, 2, 4]



}