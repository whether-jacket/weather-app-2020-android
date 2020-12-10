package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseResult
import com.seljabali.network.responses.WeatherForLocation
import com.seljabali.templateapplication.ui.weather.models.CityRegionWeather

sealed class WeatherResult : BaseResult {
    object LoadingWeatherResult : WeatherResult()
    data class WeatherForLocationResult(val response: WeatherForLocation) : WeatherResult()
    data class WeatherForAllLocationsResult(val weatherForCities: ArrayList<CityRegionWeather>) : WeatherResult()
    data class NewLocationResult(val locationPosition: Int) : WeatherResult()
    object ErrorLoadingWeatherForLocationResult : WeatherResult()
    data class ShowToastResult(val message: String): WeatherResult()
    object NoOperationResult: WeatherResult()
}
