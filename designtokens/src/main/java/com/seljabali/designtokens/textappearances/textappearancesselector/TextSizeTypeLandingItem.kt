package com.seljabali.designtokens.textappearances.textappearancesselector

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R
import com.seljabali.designtokens.LandingItem

enum class TextSizeTypeLandingItem(@StringRes override val titleStringId: Int,
                                   @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) :
    LandingItem {
//    CUSTOM(R.string.custom_sizes, android.R.color.transparent),
    APP_COMPAT(R.string.app_compat, android.R.color.transparent),
    MATERIAL(R.string.material, android.R.color.transparent),
}