package com.seljabali.templateapplication.base.basic

import com.seljabali.templateapplication.base.mvvm.ViewEvent


sealed class BasicViewEvent : ViewEvent {
    class UserClickedBack : BasicViewEvent()
    class UserHitStop : BasicViewEvent()
    class UserHitStart : BasicViewEvent()
}