package com.seljabali.designtokens.elevations

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R

enum class Elevations(@DimenRes val elevationId: Int, @StringRes val stringId: Int) {
    XS(R.dimen.xs_elevation, R.string.xs),
    S(R.dimen.s_elevation, R.string.s),
    M(R.dimen.m_elevation, R.string.m),
    L(R.dimen.l_elevation, R.string.l),
}