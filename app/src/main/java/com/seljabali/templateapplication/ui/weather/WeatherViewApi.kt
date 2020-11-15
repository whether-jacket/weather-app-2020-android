package com.seljabali.templateapplication.ui.weather

import com.seljabali.templateapplication.ui.weather.cityregionadapter.CityRegion

interface WeatherViewApi {
    fun setCityRegionsVisibility(toShow: Boolean)
    fun setCityRegions(cityRegions: List<CityRegion>)
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