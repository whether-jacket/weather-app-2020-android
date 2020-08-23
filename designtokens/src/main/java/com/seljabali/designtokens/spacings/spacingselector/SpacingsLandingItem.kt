package com.seljabali.designtokens.spacings.spacingselector

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem

enum class SpacingsLandingItem(@StringRes override val titleStringId: Int,
                          @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) :
    LandingItem {
    LINEAR(R.string.linear, android.R.color.transparent),
    NON_LINEAR(R.string.non_linear, android.R.color.transparent),
}