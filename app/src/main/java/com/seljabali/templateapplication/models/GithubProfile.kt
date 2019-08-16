package com.seljabali.templateapplication.models

import androidx.annotation.StyleRes
import com.seljabali.design.R
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class UserPreferences(
    @Id(assignable = true) var id: Long = 1L,

    @StyleRes var themeId: Int = R.style.Theme_Tokyo
) : BaseModel()