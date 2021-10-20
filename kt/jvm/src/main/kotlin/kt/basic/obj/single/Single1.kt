package kt.basic.obj.single

class Single1 {

    companion object {
        private val INSTANCE: Single1 = Single1()

        fun getInstance(): Single1 {
            return INSTANCE
        }
    }

}

object Single11 {}