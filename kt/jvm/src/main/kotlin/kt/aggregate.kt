fun main(args: Array<String>) {

    val numbers = listOf(5, 2, 10, 4)

    val sum = numbers.reduce {
        sum, element ->
        run {
            println("sum: $sum, and element: $element")
            sum + element
        }
    }
    println("reduce total sum $sum")


    val foldSum = numbers.fold(0) {
        sum, element ->
        run {
            println("sum: $sum, and element: $element")
            sum + element
        }
    }
    println("fold total sum $foldSum")

    val foldRightSum = numbers.foldRight(0) {
        element, sum ->
        run {
            println("sum: $sum, and element: $element")
            sum + element
        }
    }
    println("fold right total sum $foldRightSum")

    val foldIndexSum = numbers.foldIndexed(0) {
        idx, sum, element ->
        run {
            println("sum: $sum, and element: $element")
            if (idx % 2 == 0) {
                sum + element
            } else {
                sum
            }
        }
    }
    println("fold indexed total sum $foldIndexSum")
}