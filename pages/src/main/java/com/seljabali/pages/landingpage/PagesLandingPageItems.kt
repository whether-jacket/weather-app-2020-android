package com.seljabali.pages.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.pages.R

enum class PagesLandingPageItems(@StringRes override val titleStringId: Int,
                                 @DrawableRes override val iconId: Int = R.color.transparent) : LandingItem {
    LOGIN(R.string.login),
    MAPS(R.string.maps, R.drawable.ic_map),
    SETTINGS(R.string.settings, R.drawable.ic_settings),
}

