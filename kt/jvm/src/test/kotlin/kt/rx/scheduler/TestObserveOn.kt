package kt.rx.scheduler

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.Test

class TestObserveOn {

    @Test
    fun testObserveOn() {
        Observable.just(1)
            .map {
                println("observe map-1:" + Thread.currentThread().name)
            }
            .observeOn(Schedulers.newThread())
            .map {
                println("observe map-2:" + Thread.currentThread().name)
            }
            .observeOn(Schedulers.io())
            .map {
                println("observe map-3:" + Thread.currentThread().name)
            }
            .observeOn(Schedulers.computation())
            .subscribe {
                println("observe subscribe:" + Thread.currentThread().name)
            }
    }

}