package com.seljabali.templateapplication.base.basic

import com.seljabali.templateapplication.base.mvvm.Result

sealed class BasicResult : Result {
    class ApiCallResult : BasicResult()
}