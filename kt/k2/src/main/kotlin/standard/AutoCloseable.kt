package standard

class Resource : AutoCloseable {
    override fun close() {
        println("Resource closed")
    }

    fun performAction() {
        println("Performing action with the resource")
    }
}

fun main() {
    Resource().use { resource ->
        resource.performAction()
    }
}
