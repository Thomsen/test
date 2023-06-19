package kt.basic

fun printBitInfo() {
    println("UNSPECIFIED = $UNSPECIFIED")   // 0
    println("EXACTLY = $EXACTLY")           // 1073741824
    println("AT_MOST = $AT_MOST")           // -2147483648

    println("MODE_SHIFT bit = ${toBinary(MODE_SHIFT, 16)}")
    println("UNSPECIFIED bit = ${toBinary(UNSPECIFIED, 16)}")
    println("EXACTLY bit = ${toBinary(EXACTLY, 16)}")
    println("AT_MOST bit = ${toBinary(AT_MOST, 16)}")

    println("0x3 = ${0x3.toInt()}")

    println("width size = $widthSize")

    println("width width bit = ${toBinary(widthSpec, 16)}")
    println("width size bit = ${toBinary(widthSize, 16)}")
}

const val MODE_SHIFT = 30

// << 110000....(32)
private const val MODE_MASK = 0x3 shl MODE_SHIFT

// 00 shift left  30
const val UNSPECIFIED = 0 shl MODE_SHIFT
// 01 shift left 30
const val EXACTLY = 1 shl MODE_SHIFT
// 10 shift left 30
const val AT_MOST = 2 shl MODE_SHIFT

const val widthSpec = 1073742904
// & ~MODE_MASK
const val widthSize = widthSpec and MODE_MASK.inv()

fun toBinary(x: Int, len: Int): String {
    return String.format(
        "%" + len + "s",
        Integer.toBinaryString(x)
    ).replace(" ".toRegex(), "0")
}


fun toBinary2(x: Int, len: Int): String {
    val result = StringBuilder()
    for (i in len - 1 downTo 0)
    {
        val mask = 1 shl i
        result.append(if (x and mask != 0) 1 else 0)
    }
    return result.toString()
}

fun toBinary3(x: Int, len: Int): String {
    val buffer = CharArray(len)
    for (i in len - 1 downTo 0)
    {
        val mask = 1 shl i
        buffer[len - 1 - i] = if (x and mask != 0) '1' else '0'
    }
    return String(buffer)
}
