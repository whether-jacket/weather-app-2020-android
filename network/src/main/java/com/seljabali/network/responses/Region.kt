package com.seljabali.network.responses

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("title")
    val title: String,

    @SerializedName("location_type")
    val locationType: String,

    @SerializedName("woeid")
    val whereOnEarthId: Int,

    @SerializedName("latt_long")
    val lat_long: String,
)