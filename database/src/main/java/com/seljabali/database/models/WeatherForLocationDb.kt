package com.seljabali.database.models

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class WeatherForLocationDb(
    @Id(assignable = true) var id: Long,
    val theTemp: Float,
    val pressure: Float,
    val humidity: Float,
    val windSpeed: Float,
    val dateTimeOfFetch: String,
    val location: ToOne<LocationDb>
) : Parcelable