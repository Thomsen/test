package arrow.effect

fun main() {
    val result = TVShowFetcher.fetch("Big Bang")
    println(result)

//    TVShowFetcher.fetch("Big Bang") pipe ::println
}