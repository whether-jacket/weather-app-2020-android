package com.seljabali.core.modules

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class RxProvider {

    open fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    open fun ioScheduler(): Scheduler = Schedulers.io()

    open fun computationScheduler(): Scheduler = Schedulers.computation()

    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    fun <T> publishSubject(): PublishSubject<T> = PublishSubject.create<T>()

    fun <T> behaviorSubject(): BehaviorSubject<T> = BehaviorSubject.create<T>()
}
