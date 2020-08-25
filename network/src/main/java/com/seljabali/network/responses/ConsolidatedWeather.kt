package com.seljabali.network.responses

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(
    @SerializedName("id")
    val id: Long,

    @SerializedName("weather_state_name")
    val weatherStateName: String,

    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,

    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,

    @SerializedName("created")
    val createdDateTime: String,

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
