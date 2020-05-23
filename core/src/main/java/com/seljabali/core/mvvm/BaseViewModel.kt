package com.seljabali.core.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.seljabali.core.modules.RxProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<ViewEvent : BaseViewEvent, ViewState : BaseViewState, SideEffect : BaseSideEffect>(
    private val rxProvider: RxProvider
) : ViewModel() {

    protected var viewStateSubject = rxProvider.behaviorSubject<ViewState>()
    val viewStateEvents: Observable<ViewState> = viewStateSubject
    protected val viewEventSubject = rxProvider.behaviorSubject<ViewEvent>()

    protected var sideEffectSubject = rxProvider.behaviorSubject<SideEffect>()
    val sideEffects: Observable<SideEffect> = sideEffectSubject

    private val generalPurposeDisposables = CompositeDisposable()

    /**
     * Called when the corresponding Activity or Fragment's onCreate is called
     */
    abstract fun onCreate()

    /**
     * Called when the corresponding Activity or Fragment's onResume is called
     */
    abstract fun onResume()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        generalPurposeDisposables.dispose()
    }

    fun processViewEvents(viewEvents: Observable<ViewEvent>) {
        viewEvents.subscribe(viewEventSubject)
    }

    protected fun sendViewState(viewState: ViewState) {
        viewStateSubject.onNext(viewState)
    }

    protected fun sendSideEffect(sideEffect: SideEffect) {
        sideEffectSubject.onNext(sideEffect)
    }

    protected fun subscribe(disposable: Disposable) {
        generalPurposeDisposables.add(disposable)
    }
}