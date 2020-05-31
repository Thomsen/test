package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import kt.printBlock
import org.junit.jupiter.api.Test

class TestMap {

    @Test
    fun testMap() {
        // the test method not private
        printBlock("test map")
        val disposable = Observable.just("A", "B", "c")
            .map{ "$it map to" }
            .subscribe {
                println(it)
            }

        assert(disposable.isDisposed)
    }


}
