package com.seljabali.templateapplication.base.basic

import com.seljabali.templateapplication.base.mvvm.BaseViewModel
import com.seljabali.templateapplication.base.mvvm.ViewEvent
import io.reactivex.Observable

class BasicVM : BaseViewModel<BasicViewEvent, BasicViewState>() {

    val initialState = BasicViewState(isShowing = false)

    init {
        compositeDisposable.add()
    }

    fun setInputStream(inputStream: Observable<BasicViewEvent>) {
        inputStream
            .compose()
    }

    /**
     *  ViewEvent -> Action
     */
    fun dispatchViewEvents(viewEvent: BasicViewEvent): BasicAction {
        val action = when (viewEvent) {
            is BasicViewEvent.UserHitStop -> BasicAction.StopProgress()
            else -> BasicAction.NoOp()
        }
        return action
    }

    /**
     *  Action -> Result
     */
    fun dispatchAction(action : BasicAction) {

    }

    /**
     *  Result -> ViewState
     */
    fun dispatchResult(result: BasicResult) {
        val currentState = initialState
        val newState = currentState.copy()
        when (result) {
            is BasicResult.ApiCallResult -> newState
        }
        outputStream.onNext(newState)
    }
}