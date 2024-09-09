package cast
interface State {
    fun foo() {}
}

interface Success : State {
    fun success() {}
}
interface Error : State {
    fun error() {}
}
interface Cancel


fun fooCheck(status: Any) {
    if (status is Success || status is Cancel) {
//        status.success()
//        status.error()
//        status.foo()
    }
    if (status is Success || status is Error) {
//        status.foo()
    }
}

fun main() {
    fooCheck(object: Success {

    })
}