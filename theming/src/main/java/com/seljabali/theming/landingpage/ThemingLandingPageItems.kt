package com.seljabali.theming.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.theming.R

enum class ThemingLandingPageItems(@StringRes override val titleStringId: Int,
                                   @DrawableRes override val iconId: Int = R.color.transparent) : LandingItem {
    THEME_SELECTOR(R.string.theme_selector, R.drawable.ic_styles),
    THEME_PREVIEW(R.string.theme_preview, R.drawable.ic_palette),
}

