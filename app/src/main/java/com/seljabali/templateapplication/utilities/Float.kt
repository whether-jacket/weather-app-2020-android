package com.seljabali.templateapplication.utilities

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Float.getWholeAmount(): Int = this.toInt()

fun Float.round(decimalPlaces: Int): Float = BigDecimal(this.toString()).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP).toFloat()

fun Float.roundUp(decimalPlaces: Int): Float = BigDecimal(this.toString()).setScale(decimalPlaces, BigDecimal.ROUND_UP).toFloat()

fun Float.Companion.getValueOrZero(float: String): Float {
    if (float.isEmpty()) {
        return 0f
    }
    return try {
        java.lang.Float.valueOf(float)
    } catch (e: NumberFormatException) {
        0f
    }
}

fun Float.getAmountInUSDFormat(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    formatter.maximumFractionDigits = 2
    return formatter.format(this.toDouble()).trim { it <= ' ' }
}

fun Float.getAmountIntegerInUSDFormat(): String = getAmountIntegerInUSDFormat(2)

fun Float.getAmountIntegerInUSDFormat(fractionDigits: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    formatter.maximumFractionDigits = fractionDigits
    return formatter.format(this.toDouble())
}