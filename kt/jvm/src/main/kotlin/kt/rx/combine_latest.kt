package kt.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.subjects.PublishSubject

fun combineLatest() {

    val name = PublishSubject.create<String>()

    val age = PublishSubject.create<Int>()

    // Can not omit Type parameters and BiFunction
    Observable.combineLatest<String, Int, String>(
        name, age, BiFunction { n, a -> "$n - age:${a}" })
        .subscribe {
            println("onNext - ${it}")
        }

    // If you introduce RxKotlin then you can use type inference
    Observables.combineLatest(name, age) { n, a -> "$n - age:${a}" }
        .subscribe {
            println("onNext with RxKotlin - ${it}")
        }

    // Also we can use Observable array for 1st parameter
    // but second parameter to be array, it's not cool.
//    Observables.combineLatest(arrayOf(name, age), {
//        val n = it[0] as String
//        val a = it[1] as Int
//        "$n - age:${a}" })
//        .subscribe({
//            println("onNext - ${it}")
//        })


    name.onNext("saito")
    age.onNext(24)
    name.onNext("yoshida")
}