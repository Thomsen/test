package kt.patterns.solid

// single responsibility principle

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
}

class AuthenticationService {
    fun signIn() {
        // this method will do signing in operations
    }

    fun signOut() {
        // this method will do signing out operations
    }
}