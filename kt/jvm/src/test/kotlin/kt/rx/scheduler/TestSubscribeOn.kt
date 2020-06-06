package kt.rx.scheduler

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

class TestSubscribeOn {

    @Test
    fun testSubscribeOn() {
        Observable.just(1)
            .map(object : io.reactivex.rxjava3.functions.Function<Int, Any>{
                @Throws(Exception::class)
                override fun apply(@NonNull integer: Int?): Int? {
                    println("map-1: $integer " + Thread.currentThread().name)
                    return integer
                }
            })
            .subscribeOn(Schedulers.newThread())
            .map {
                println("map-2:" + Thread.currentThread().name)
            }
            .subscribeOn(Schedulers.io())  // io intensive task
            .map {
                println("map-3:" + Thread.currentThread().name)
            }
            .subscribeOn(Schedulers.computation())  // cpu intensive task
            .subscribe {
                println("subscribe:" + Thread.currentThread().name)
            }
    }
}