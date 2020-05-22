package com.seljabali.database.models

import android.os.Parcelable
import androidx.annotation.StyleRes
import com.seljabali.database.R
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class UserPreferences(
    @Id(assignable = true) var id: Long = 1L,

    @StyleRes var themeId: Int = R.style.Theme_Tokyo
) : Parcelable