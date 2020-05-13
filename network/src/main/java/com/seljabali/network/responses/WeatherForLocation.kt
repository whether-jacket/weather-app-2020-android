package com.seljabali.network.responses

import com.google.gson.annotations.SerializedName

data class WeatherForLocation(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: Array<ConsolidatedWeather>
)

data class ConsolidatedWeather(
    @SerializedName("id")
    val id: Long,

    @SerializedName("weather_state_name")
    val weatherStateName: String,

    @SerializedName("weather_state_abbr")
    val weather_state_abbr: String,

    @SerializedName("wind_direction_compass")
    val wind_direction_compass: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("applicable_date")
    val applicableDate: String,

    @SerializedName("min_temp")
    val minTemp: Float,

    @SerializedName("max_temp")
    val maxTemp: Float,

    @SerializedName("the_temp")
    val theTemp: Float,

    @SerializedName("wind_speed")
    val windSpeed: Float,

    @SerializedName("wind_direction")
    val windDirection: Float,

    @SerializedName("air_pressure")
    val airPressure: Float,

    @SerializedName("humidity")
    val humidity: Float,

    @SerializedName("visibility")
    val visibility: Float,

    @SerializedName("predictability")
    val predictability: Float
)
