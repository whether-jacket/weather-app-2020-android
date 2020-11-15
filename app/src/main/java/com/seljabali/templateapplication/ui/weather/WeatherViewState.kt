package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewState
import com.seljabali.templateapplication.ui.weather.models.CityRegionWeather

data class WeatherViewState(
    var cityRegionWeatherList: ArrayList<CityRegionWeather> = ArrayList(),
    var selectedCityRegionPosition: Int = 0,
    var isLoadingTemperature: Boolean = false
) : BaseViewState
