package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseAction

abstract class WeatherAction : BaseAction {
    object NoOperationAction : WeatherAction()
}

sealed class WeatherRepoAction: WeatherAction() {
    data class FetchForLocationAction(val whereOnEarthId: Int): WeatherAction()
    data class FetchForSearchLocationAction(val cityName: String): WeatherAction()
}
