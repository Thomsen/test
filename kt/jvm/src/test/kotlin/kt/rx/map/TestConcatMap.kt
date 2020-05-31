package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kt.printBlock
import org.junit.jupiter.api.Test

class TestConcatMap {

    @Test
    fun testConcatMap() {
        printBlock("test concat map")

        Observable.just("A", "B", "c")
            .concatMap {
                //            Observable.just(it)
                Observable.just(it).subscribeOn(Schedulers.io())
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun testMerge() {
        printBlock("test merge")

        val o1 = Observable.just("1", "2", "3")
        val o2 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
        .concatMap {
            println("concat map")
            Observable.just("concat map $it").subscribeOn(Schedulers.io())
        }
            .subscribe {
                println("merge $it")
            }
    }

}
