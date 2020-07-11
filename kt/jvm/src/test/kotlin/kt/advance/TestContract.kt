package kt.advance

import org.junit.Test
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class TestContract {

    @Test
    fun testList() {
        val data: List<String>? = listOf("ab")
        if (!data.isNullOrEmpty()) {
            println(data.size)

            val someElement: String
            data.let {
                someElement = it.last()
            }
        }
    }

}

@OptIn(ExperimentalContracts::class)
fun <T> Collection<T>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return this == null || this.isEmpty()
}

@OptIn(ExperimentalContracts::class)
inline fun <T, R> T.let(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(this)
}