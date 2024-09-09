package standard

class CustomMutableList<E> : AbstractMutableList<E>() {
    private val internalList = mutableListOf<E>()
    private var count = 0

    override val size: Int
        get() = internalList.size

    override fun add(index: Int, element: E) {
        internalList.add(index, element)
        count++
    }

    override fun removeAt(index: Int): E {
        count++
        return internalList.removeAt(index)
    }

    override fun set(index: Int, element: E): E {
        internalList[index] = element
        return element
    }

    override fun get(index: Int): E {
        return internalList[index]
    }

    override fun isEmpty(): Boolean {
        return internalList.isEmpty()
    }

    fun getModCount(): Int {
        val count = modCount
        return count
    }


}

fun main() {
    val customList = CustomMutableList<Int>()
    customList.add(0, 10)
    customList.add(1, 20)

    println("Size of list: ${customList.size}")
    println("Modification count: ${customList.getModCount()}")

    customList.removeAt(1)

    println("Size of list: ${customList.size}")
    println("Modification count: ${customList.getModCount()}")

}