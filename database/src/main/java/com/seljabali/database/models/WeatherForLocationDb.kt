package com.seljabali.database.models

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class WeatherForLocationDb(
    @Id(assignable = true) var id: Long,
    val theTemp: Float,
    val state: String,
    val cityName: String
) : Parcelable