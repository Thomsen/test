package kt.thread

class LocalData<T> {

    var data: T? = null

    fun set(value: T) {
        this.data = value
    }

    fun get(): T? {
        return data
    }

    fun remove() {

    }
}