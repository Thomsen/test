package kt.rx.error

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Emitter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.BooleanSupplier
import io.reactivex.rxjava3.functions.Supplier
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.LongAdder

class TestError {

    @Test
    fun testDoOnError() {
        val disposable = Observable.error<String>(IOException("Something went wrong"))
            .doOnError { error -> System.err.println("The error message is: " + error.message) }
            .subscribe(
                { x -> println("onNext should never be printed!") },
                { e -> e.printStackTrace() },
                {
                    println("onComplete should never be printed!")
                }
            )
        assert(disposable.isDisposed)
    }

    @Test
    fun testOnErrorComplete() {
        val disposable = Completable.fromAction { throw IOException() }
            .onErrorComplete { error: Throwable? -> error is IOException }.subscribe(
                { println("IOException was ignored") }
            ) { error: Throwable? ->
                System.err.println(
                    "onError should not be printed!"
                )
            }
        assert(disposable.isDisposed)
    }

    @Test
    fun testOnErrorResumeNext() {
        val numbers =
            Observable.generate(
                Supplier { 1 },
                BiFunction { state: Int, emitter: Emitter<Int> ->
                    emitter.onNext(state)
                    state + 1
                }
            )

        val disposable = numbers.scan { x: Int?, y: Int? ->
            Math.multiplyExact(
                x!!,
                y!!
            )
        }
            .onErrorResumeNext { Observable.empty<Int>()}
            .subscribe(
                { x: Int? -> println(x) }
            ) { error: Throwable? ->
                System.err.println(
                    "onError should not be printed!"
                )
            }
        assert(disposable.isDisposed)
    }

    @Test
    fun testOnErrorReturn() {
        val disposable = Single.just("2A")
            .map({ v -> v.toInt(10) })
            .onErrorReturn({ error -> if (error is NumberFormatException) return@onErrorReturn 0 else throw IllegalArgumentException() })
            .subscribe(
                System.out::println,
                { error -> System.err.println("onError should not be printed!") })
        assert(disposable.isDisposed)
    }

    @Test
    fun testOnErrorReturnItem() {
        val disposable = Single.just("2A")
            .map({ v -> v.toInt(10) })
            .onErrorReturnItem(0)
            .subscribe(
                System.out::println,
                { error -> System.err.println("onError should not be printed!") })
        assert(disposable.isDisposed)
    }

//    fun testOnExceptionResumeNext() {
//        val exception: Observable<String> =
//            Observable.error<String> { IOException() }
//                .onExceptionResumeNext(Observable.just("This value will be used to recover from the IOException"))
//
//        val error: Observable<String> =
//            Observable.error<String> { Error() }
//                .onExceptionResumeNext(Observable.just("This value will not be used"))
//
//        Observable.concat(exception, error)
//            .subscribe(
//                { message: String ->
//                    println(
//                        "onNext: $message"
//                    )
//                }
//            ) { err: Throwable ->
//                System.err.println(
//                    "onError: $err"
//                )
//            }
//    }

    @Test
    fun testRetry() {
        val source =
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap { x: Long ->
                    if (x >= 2) return@flatMap Observable.error<Long>(
                        IOException("Something went wrong!")
                    ) else return@flatMap Observable.just(
                        x
                    )
                }

        val disposable = source.retry { retryCount: Int, error: Throwable? -> retryCount < 3 }
            .blockingSubscribe(
                { x: Long -> println("onNext: $x") }
            ) { error: Throwable ->
                System.err.println(
                    "onError: " + error.message
                )
            }
        assert(disposable != null)
    }

    @Test
    fun testRetryUnitl() {
        val errorCounter = LongAdder()
        val source =
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap { x: Long ->
                    if (x >= 2) return@flatMap Observable.error<Long>(
                        IOException("Something went wrong!")
                    ) else return@flatMap Observable.just(
                        x
                    )
                }
                .doOnError { error: Throwable? -> errorCounter.increment() }

        val disposable = source.retryUntil(BooleanSupplier { (errorCounter.toInt() >= 3) })
            .blockingSubscribe(
                { x: Long -> println("onNext: $x") }
            ) { error: Throwable ->
                System.err.println(
                    "onError: " + error.message
                )
            }
    }

    @Test
    fun testRetryWhen() {
        val source =
            Observable.interval(
                0,
                1,
                TimeUnit.SECONDS
            )
                .flatMap { x: Long ->
                    if (x >= 2) return@flatMap Observable.error<Long>(
                        IOException("Something went wrong!")
                    ) else return@flatMap Observable.just(
                        x
                    )
                }

        val disposable = source.retryWhen { errors: Observable<Throwable> ->
            errors.map { error: Throwable -> 1 } // Count the number of errors.
                .scan { x: Int?, y: Int? ->
                    Math.addExact(
                        x!!,
                        y!!
                    )
                }
                .doOnNext { errorCount: Int ->
                    println(
                        "No. of errors: $errorCount"
                    )
                } // Limit the maximum number of retries.
                .takeWhile { errorCount: Int -> errorCount < 3 } // Signal resubscribe event after some delay.
                .flatMapSingle<Any?> { errorCount: Int? ->
                    Single.timer(
                        errorCount?.toLong() ?: 5,
                        TimeUnit.SECONDS
                    )
                }
        }.blockingSubscribe(
            { x: Long -> println("onNext: $x") },
            { obj: Throwable -> obj.printStackTrace() }
        ) { println("onComplete") }

    }
}