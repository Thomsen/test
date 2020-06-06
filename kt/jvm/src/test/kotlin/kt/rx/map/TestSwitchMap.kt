package kt.rx.map

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kt.printBlock
import org.junit.jupiter.api.Test

class TestSwitchMap {

    @Test
    fun testSwitchMapJust() {
        printBlock("test switch map just")
        Observable.just("A", "B", "c")
            .switchMap {
                Observable.just(it)
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun testSwitchMap() {
        printBlock("test switch map")
        Observable.just("A", "B", "c")
            .switchMap {
                Observable.just(it).subscribeOn(Schedulers.io())
            }
            .subscribe {
                println(it)
            }
    }

    @Test
    fun testSwitchJustAfterMerge() {
        printBlock("test merge just")

        val o1 = Observable.just("1", "2", "3")
        val o2 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
            .switchMap {
                println("switch map")
                Observable.just("switch map just $it")
            }
            .subscribe {
                println("merge $it")
            }
    }

    @Test
    fun testSwitchAfterMerge() {
        printBlock("test merge in io")

        val o1 = Observable.just("1", "2", "3")
        val o2 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
            .switchMap {
                println("switch map $it")
                Observable.just("switch map $it in io()").subscribeOn(Schedulers.io())
            }
            .subscribe {
                println("merge $it")
            }
    }

    @Test
    fun testSwitchAfterMerge2() {
        printBlock("test merge 2 in io")

        val o2 = Observable.just("1", "2", "3")
        val o1 = Observable.just("x", "y", "z")
        Observable.merge(o1, o2)
            .switchMap {
                println("switch map $it")
                Observable.just("switch map $it in io()").subscribeOn(Schedulers.io())
            }
            .subscribe {
                println("merge $it")
            }
    }

}
