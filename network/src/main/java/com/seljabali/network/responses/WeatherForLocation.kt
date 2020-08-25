package com.seljabali.network.responses

import com.google.gson.annotations.SerializedName

data class WeatherForLocation(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: Array<ConsolidatedWeather>,

    @SerializedName("parent")
    val parentRegion: Region,

    @SerializedName("woeid")
    val whereOnEarthId: Int,

    @SerializedName("latt_long")
    val lat_long: String,

    @SerializedName("title")
    val cityTitle: String,

    @SerializedName("location_type")
    val location_type: String,

    @SerializedName("time")
    val dateTime: String,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("sun_rise")
    val sunriseDateTime: String,

    @SerializedName("sun_set")
    val sunsetDateTime: String,

    @SerializedName("timezone_name")
    val timezoneName: String
)