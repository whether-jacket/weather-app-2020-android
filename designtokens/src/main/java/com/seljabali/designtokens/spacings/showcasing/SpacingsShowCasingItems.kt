package com.seljabali.designtokens.spacings.showcasing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem

enum class SpacingsShowCasingItems(@StringRes override val titleStringId: Int,
                                   @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) :
    LandingItem {
    VERTICAL(R.string.vertical, R.mipmap.vertical_spacing),
    HORIZONTAL(R.string.horizontal, R.mipmap.spacing),
    PADDING(R.string.surrounding, R.mipmap.padding),
}