package com.seljabali.database.models

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class LocationDb(
    @Id(assignable = true) var id: Long,
    val cityName: String,
    val regionName: String = "",
    val woeId: Int = 0
) : Parcelable