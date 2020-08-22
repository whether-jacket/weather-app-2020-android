package com.seljabali.core.mvi

interface BaseViewStateBinder<ViewState: BaseViewState> {
    fun unbindView()
    fun setViewState(viewState: ViewState)
}