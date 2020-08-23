package com.seljabali.designtokens.spacings.padding

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R

enum class Paddings(@DimenRes val spacingId: Int, @StringRes val stringId: Int) {
    XXS(R.dimen.xxs_padding, R.string.xxs),
    XS(R.dimen.xs_padding, R.string.xs),
    S(R.dimen.s_padding, R.string.s),
    M(R.dimen.m_padding, R.string.m),
    L(R.dimen.l_padding, R.string.l),
    XL(R.dimen.xl_padding, R.string.xl),
    XXL(R.dimen.xxl_padding, R.string.xxl)
}