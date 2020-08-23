package com.seljabali.designtokens.letterspacings

import androidx.annotation.AnyRes
import androidx.annotation.StringRes
import com.seljabali.designtokens.R

enum class LetterSpacings(@AnyRes val floatId: Int, @StringRes val stringId: Int) {
    XXS(R.dimen.xxs_letter_spacing, R.string.xxs),
    XS(R.dimen.xs_letter_spacing, R.string.xs),
    S(R.dimen.s_letter_spacing, R.string.s),
    M(R.dimen.m_letter_spacing, R.string.m),
    L(R.dimen.l_letter_spacing, R.string.l),
    XL(R.dimen.xl_letter_spacing, R.string.xl),
    XXL(R.dimen.xxl_letter_spacing, R.string.xxl)
}