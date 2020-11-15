package com.seljabali.templateapplication.ui.weather.models

data class CityRegionWeather(
    val cityName: String,
    val regionName: String,
    val woeId: Int,
    val currentTemperature: String,
    val humidity: String,
    val windSpeed: String,
    val pressure: String,
    val dateTime: String
)