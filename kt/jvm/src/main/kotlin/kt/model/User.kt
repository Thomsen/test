package kt.model

class User {
    var id: Int = 0
    var name: String? = null
    var hobbies: List<String>? = null

    override fun toString(): String {
        return "User(id=$id, name=$name, hobbies=$hobbies)"
    }
}
