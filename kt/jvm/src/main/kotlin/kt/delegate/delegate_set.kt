package kt.delegate

// inheritance ( is-a )
class CountingSet1<T>: HashSet<T>() {
    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded ++
        return super.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return super.addAll(elements)
    }
}

// composition ( has-a )
class CountingSet2<T>: MutableSet<T> {

    private val innerSet = HashSet<T>()

    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded ++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }

    override fun clear() {
        innerSet.clear()
    }

    override fun iterator(): MutableIterator<T> {
        return innerSet.iterator()
    }

    override fun remove(element: T): Boolean {
        return innerSet.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return innerSet.removeAll(elements)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return innerSet.retainAll(elements)
    }

    override val size: Int
        get() = innerSet.size

    override fun contains(element: T): Boolean {
        return innerSet.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return innerSet.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return innerSet.isEmpty()
    }

}

// delegation ( target is method )
class CountingSet3<T> (
    private val innerSet: MutableSet<T> = HashSet<T> ()
): MutableSet<T> by innerSet {
    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded ++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}