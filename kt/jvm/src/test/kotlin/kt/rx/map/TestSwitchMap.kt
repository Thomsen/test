package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kt.printBlock
import org.junit.jupiter.api.Test

class TestSwitchMap {



    @Test
    fun testSwitchMap() {
        printBlock("test switch map")
        Observable.just("A", "B", "c")
            .switchMap {
                //            Observable.just(it)
                Observable.just(it).subscribeOn(Schedulers.io())
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun testSwitchAfterMerge() {
        printBlock("test merge")

        val o1 = Observable.just("1", "2", "3")
        val o2 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
            .switchMap {
                println("switch map")
            Observable.just("switch map $it in io()").subscribeOn(Schedulers.io())
                Observable.just("switch map $it")
            }
            .subscribe {
                println("merge $it")
            }
    }

}
