package com.km2

import kotlin.collections.AbstractMutableList

class CustomList<E> : AbstractMutableList<E>() {
    private val innerList = mutableListOf<E>()

    override val size: Int
        get() = innerList.size

    override fun get(index: Int): E {
        return innerList[index]
    }

    override fun add(index: Int, element: E) {
        innerList.add(index, element)
        modCount++
    }

    override fun removeAt(index: Int): E {
        val removedElement = innerList.removeAt(index)
        modCount++
        return removedElement
    }

    override fun set(index: Int, element: E): E {
        val oldElement = innerList.set(index, element)
        modCount++
        return oldElement
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        innerList.subList(fromIndex, toIndex).clear()
        modCount++
    }

    fun rm(fromIndex: Int, toInt: Int) {
        removeRange(fromIndex, toInt)
    }
}

fun main() {
    val l = CustomList<Int>()

    l.add(1)
    l.add(2)
    l.add(3)

    println(l)

    l.rm(0, 1)
    println(l)
}

