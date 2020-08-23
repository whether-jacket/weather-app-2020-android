package com.seljabali.designtokens.cornerradiuses

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R

enum class CornerRadiuses(@DimenRes val spacingId: Int, @StringRes val stringId: Int) {
    XS(R.dimen.xs_corner_radius, R.string.xs),
    S(R.dimen.s_corner_radius, R.string.s),
    M(R.dimen.m_corner_radius, R.string.m),
}