package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewEvent

sealed class WeatherViewEvent : BaseViewEvent {
    object LoadWeatherPageEvent : WeatherViewEvent()
    object LoadNextCityEvent : WeatherViewEvent()
    object LoadPreviousCityEvent : WeatherViewEvent()
}
