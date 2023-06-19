package kt.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

fun empty() {
    val upstream = PublishSubject.create<Int>()
    upstream.doOnDispose { println("Uh, the upstream gets disposed") } // This log will never print.

    val tester = upstream
        .flatMap { v ->
            when (v) {
                0 -> {
                    println("flatMap produces an empty")
                    Observable.empty<Int>()
                }
                else -> {
                    println("flatMap produces just value")
                    Observable.just(v)
                }
            }
        }
        .test()

    upstream.onNext(0) // Will return a complete in flatMap.
    upstream.onNext(1) // If the stream dies, we'll never emit this value successfully.

    tester.assertValueCount(1) // Actually, the stream is still alive because flatMap ignores the complete!

}