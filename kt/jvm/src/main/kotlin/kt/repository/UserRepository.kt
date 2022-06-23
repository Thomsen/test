package kt.repository

class UserRepository {

    val users = mutableListOf<String>()

    suspend fun register(user: String) {
        users.add(user)
    }

    fun getAllUsers(): List<String> {
        return users
    }
}