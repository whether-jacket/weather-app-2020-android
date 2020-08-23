package com.seljabali.designtokens.transparency

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R

enum class Transparencies(@DimenRes val transparencyId: Int, @StringRes val stringId: Int) {
    XXS(R.dimen.xxs_transparency, R.string.xxs),
    XS(R.dimen.xs_transparency, R.string.xs),
    S(R.dimen.s_transparency, R.string.s),
    M(R.dimen.m_transparency, R.string.m),
    L(R.dimen.l_transparency, R.string.l),
    XL(R.dimen.xl_transparency, R.string.xl),
    XXL(R.dimen.xxl_transparency, R.string.xxl),
}