package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewState

data class WeatherViewState(
    var city: String = "",
    var greaterRegion: String = "",
    var pressure: String = "",
    var humidity: String = "",
    var windSpeed: String = "",
    var dateTime: String = "",
    var currentTemperature: String = "",
    var isLoadingTemperature: Boolean = false
) : BaseViewState
