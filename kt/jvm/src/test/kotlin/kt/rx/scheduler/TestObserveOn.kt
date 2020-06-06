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
            .subscribeOn(Schedulers.newThread())  // thread 4
            .subscribe {
                println("observe subscribe:" + Thread.currentThread().name)
            }
    }

    // not the thread 4
//    observe map-1:main
//    observe map-2:RxNewThreadScheduler-1
//    observe map-3:RxCachedThreadScheduler-1
//    observe subscribe:RxComputationThreadPool-1

    // have the thread 4
//    observe map-1:RxNewThreadScheduler-1
//    observe map-2:RxNewThreadScheduler-2
//    observe map-3:RxCachedThreadScheduler-1
//    observe subscribe:RxComputationThreadPool-1
    // subscribeOn do on the first call

}