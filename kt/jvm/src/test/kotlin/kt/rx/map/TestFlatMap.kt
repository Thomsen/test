package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import kt.printBlock
import org.junit.jupiter.api.Test

class TestFlatMap {

    @Test
    fun testFlatMap() {
        printBlock("test flat map")
        val disposable = Observable.just("A", "B", "c")
            .flatMap {
                Observable.just(it)
            }
            .subscribe {
                println(it)
            }

        assert(disposable.isDisposed)
    }



}
