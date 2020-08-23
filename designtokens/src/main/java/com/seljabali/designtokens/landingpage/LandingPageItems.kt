package com.seljabali.designtokens.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem

enum class LandingPageItems(@StringRes override val titleStringId: Int,
                            @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) :
    LandingItem {
    SPACING(R.string.spacings, R.mipmap.spacing),
    TEXT_SIZES(R.string.text_appearances, R.mipmap.text_size),
    CORNER_RADIUS(R.string.corner_radiuses, R.mipmap.corner_radius),
//    ELEVATIONS(R.string.elevations, android.R.color.transparent),
    LETTER_SPACINGS(R.string.letter_spacings, R.mipmap.letter_spacing),
    COLORS(R.string.colors, R.mipmap.colors),
    TRANSPARENCIES(R.string.transparencies, R.mipmap.transparency),
}