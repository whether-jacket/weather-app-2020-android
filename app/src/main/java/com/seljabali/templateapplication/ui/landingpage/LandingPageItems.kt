package com.seljabali.templateapplication.ui.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.templateapplication.R


enum class LandingPageItems(@StringRes override val titleStringId: Int,
                            @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) : LandingItem {
    WIDGETS(R.string.widgets, R.drawable.ic_widgets),
    TEST(R.string.test, R.color.transparent),
    DESIGN(R.string.design, R.drawable.ic_paint),
    PAGES(R.string.pages, R.drawable.ic_collections),
}

