package com.seljabali.templateapplication.base.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<E : ViewEvent, S : ViewState> : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected var inputStream = PublishSubject.create<E>()
    protected val outputStream = PublishSubject.create<S>()

    fun renderViewState(): Observable<S> = outputStream

    fun unbind() {
        compositeDisposable.clear()
    }
}