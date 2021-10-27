package kt.model

import kotlin.jvm.internal.Intrinsics
import kotlin.random.Random

class User {
    var id: Int = 0
    var name: String? = null
    var hobbies: List<String>? = null

    override fun toString(): String {
        return "User(id=$id, name=$name, hobbies=$hobbies)"
    }

    constructor() {
    }

    constructor(id: Int, name: String?, hobbies: List<String>?) {
        this.id = id
        this.name = name
        this.hobbies = hobbies
    }
}

data class UserData (
    val id: Int = 0,
    var name: String? = null,
    val hobbies: List<String>? = null
)

class User2 {
    var id: Int = 0
    var name: String? = null
    var hobbies: List<String>? = null

    override fun toString(): String {
        return "User2(id=$id, name=$name, hobbies=$hobbies)"
    }

    constructor() {
    }

    constructor(id: Int, name: String?, hobbies: List<String>?) {
        this.id = id
        this.name = name
        this.hobbies = hobbies
    }

    override fun hashCode(): Int {
        var var10000 = Integer.hashCode(this.id) * 31
        val var10001 = this.name
        var10000 = (var10000 + (if (var10001 != null) var10001.hashCode() else 0)) * 31
        val var10002 = this.hobbies
        return var10000 + (if (var10002 != null) var10002.hashCode() else 0)
//        return Random.nextInt()
    }

    override fun equals(other: Any?): Boolean {
        if (other is User2) {
            if (this.id == other.id && Intrinsics.areEqual(this.name, other.name)
                    && Intrinsics.areEqual(this.hobbies, other.hobbies)) {
                return true
            }
        }

        return false
    }

}
