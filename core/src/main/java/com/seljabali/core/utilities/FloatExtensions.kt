package com.seljabali.core.utilities

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Float.getWholeAmount(): Int = this.toInt()

fun Float.round(decimalPlaces: Int): Float = BigDecimal(this.toString()).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP).toFloat()

fun Float.roundUp(decimalPlaces: Int): Float = BigDecimal(this.toString()).setScale(decimalPlaces, BigDecimal.ROUND_UP).toFloat()

fun Float.Companion.valueOfOrZero(float: String): Float {
    if (float.isEmpty()) {
        return 0f
    }
    return try {
        java.lang.Float.valueOf(float)
    } catch (e: NumberFormatException) {
        0f
    }
}

fun Float.asUSD(fractionDigits: Int = 2): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    formatter.maximumFractionDigits = fractionDigits
    return formatter.format(this.toDouble())
}