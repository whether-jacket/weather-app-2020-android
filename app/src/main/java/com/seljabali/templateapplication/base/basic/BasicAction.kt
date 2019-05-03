package com.seljabali.templateapplication.base.basic

import com.seljabali.templateapplication.base.mvvm.Action

sealed class BasicAction : Action {
    class StartProgress() : BasicAction()
    class StopProgress() : BasicAction()
    class NoOp() : BasicAction()
}