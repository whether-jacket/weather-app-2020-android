package com.seljabali.templateapplication.ui.weather


interface WeatherViewApi {
    fun setTemperature(text: String)
    fun setProgressBarVisibility(isVisible: Boolean)
}