package com.seljabali.design.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.design.R

enum class DesignLandingPageItems(@StringRes override val titleStringId: Int,
                                  @DrawableRes override val iconId: Int = R.color.transparent) : LandingItem {
    THEME_SELECTOR(R.string.theme_selector, R.color.transparent),
    THEME_PREVIEW(R.string.theme_preview, R.color.transparent),
}

