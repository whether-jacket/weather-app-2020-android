package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseViewEvent

sealed class WeatherViewEvent : BaseViewEvent {
    object LoadSfWeatherEvent : WeatherViewEvent()
}
