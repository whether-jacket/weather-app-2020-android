package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseAction

abstract class WeatherAction : BaseAction {
    object NoOperationAction : WeatherAction()
}

sealed class WeatherRepoAction: WeatherAction() {
    data class FetchForLocationAction(val locationId: Int): WeatherAction()
}

sealed class WeatherSideEffectsAction: WeatherAction() {
    data class ShowToast(val message: String) : WeatherAction()
}
