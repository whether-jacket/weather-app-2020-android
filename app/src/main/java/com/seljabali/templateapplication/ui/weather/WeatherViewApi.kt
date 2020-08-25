package com.seljabali.templateapplication.ui.weather


interface WeatherViewApi {
    fun setCity(text: String)
    fun setParentRegion(text: String)
    fun setTemperature(text: String)
    fun setWeatherImageVisibility(toShow: Boolean)
    fun setPressureTitleVisibility(toShow: Boolean)
    fun setPressure(text: String)
    fun setHumidity(text: String)
    fun setHumidityTitleVisibility(toShow: Boolean)
    fun setWindSpeed(text: String)
    fun setWindSpeedTitleVisibility(toShow: Boolean)
    fun setDateTime(text: String)
    fun setProgressBarVisibility(toShow: Boolean)
}