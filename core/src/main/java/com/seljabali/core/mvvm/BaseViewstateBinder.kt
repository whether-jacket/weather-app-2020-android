package com.seljabali.core.mvvm

interface BaseViewStateBinder<ViewState: BaseViewState> {
    fun unbindView()
    fun setViewState(viewState: ViewState)
}