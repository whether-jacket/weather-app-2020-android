package com.seljabali.database.models

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class WeatherForLocationDb(
    @Id var id: Long = 0L,
    var theTemp: Float = 0f,
    var pressure: Float = 0f,
    var humidity: Float = 0f,
    var windSpeed: Float = 0f,
    var dateTimeOfFetch: String = "",

) : Parcelable {
    lateinit var location: ToOne<LocationDb>
}