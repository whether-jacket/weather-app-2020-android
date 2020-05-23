package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseResult
import com.seljabali.network.responses.WeatherForLocation

sealed class WeatherResult : BaseResult {
    object LoadingWeatherResult : WeatherResult()
    data class WeatherForLocationResult(val response: WeatherForLocation) : WeatherResult()
    object ErrorLoadingWeatherForLocationResult : WeatherResult()
    data class ShowToastResult(val message: String): WeatherResult()
    object NoOperationResult: WeatherResult()
}
