package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kt.printBlock
import org.junit.jupiter.api.Test

class TestConcatMap {

    @Test
    fun testConcatMapJust() {
        printBlock("test concat map just")

        Observable.just("A", "B", "c")
            .concatMap {
                Observable.just(it)
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun testConcatMap() {
        printBlock("test concat map")

        Observable.just("A", "B", "c")
            .concatMap {
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
            println("concat map $it")
            Observable.just("concat map $it")
        }
            .subscribe {
                println("merge $it")
            }
    }

    @Test
    fun testSwitchIoMerge() {
        printBlock("test merge in io")

        val o1 = Observable.just("1", "2", "3")
        val o2 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
            .concatMap {
                println("concat map $it")
                Observable.just("concat map $it in io()").subscribeOn(Schedulers.io())
            }
            .subscribe {
                println("merge $it")
            }
    }


}
