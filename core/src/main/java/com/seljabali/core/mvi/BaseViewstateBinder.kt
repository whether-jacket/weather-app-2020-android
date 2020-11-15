package com.seljabali.core.mvi

interface BaseViewStateBinder<ViewState: BaseViewState> {
    fun setViewState(viewState: ViewState)
}