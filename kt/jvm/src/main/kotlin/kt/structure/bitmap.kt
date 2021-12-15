package kt.structure

import kotlin.experimental.and
import kotlin.experimental.or

class bitmap {

    private var array: ByteArray = byteArrayOf()

    private var capacity: Int = 0

    constructor(capacity: Int) {
        check(capacity > 0)
        this.capacity = capacity
        this.array = ByteArray(size = capacity / 8 + 1)
    }

    fun add(n: Int) {
        val index = n shr 3
        val position = n and 0x07
        array[index] = array[index].or((position shl 1).toByte())
    }

    fun delete(n: Int) {
        val index = n shr 3
        val position = n and 0x7
        array[index] = array[index].and((position shl 1).inv().toByte())
    }

    fun contain(n: Int): Boolean {
        val index = n shr 3
        val position = n and 0x07
        return (array[index] and (position shl 1).toByte()).toInt() != 0
    }

}