package kt.patterns.solid

// single responsibility principle (SRP)

data class User (
    var id: Long,
    var name: String,
    var password: String,
) {
//    fun signIn() {
//        // this method will do signing in operations
//    }
//
//    fun signOut() {
//        // this method will do signing out operations
//    }

//    fun saveUserToDatabase() {
//        // this method will save user to database
//    }

}

class AuthenticationService {
    fun signIn() {
        // this method will do signing in operations
    }

    fun signOut() {
        // this method will do signing out operations
    }
}

class UserRepository {
    fun saveUserToDatabase(user: User) {
        // repository only for user operation
    }
}