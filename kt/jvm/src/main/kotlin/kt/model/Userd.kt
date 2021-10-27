package kt.model


class UserD1(
    // member final param
    private val p: String
) {
    init {
        print("init $p")
    }
}

class UserD2(
    // constructor param
    p: String
) {
    init {
        print("init $p")
    }
}