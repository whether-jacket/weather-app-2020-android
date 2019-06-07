package com.seljabali.templateapplication.utilities

fun Double.Companion.valueOfOrZero(double: String): Double =
    if (double.isEmpty()) {
        0.0
    } else {
        try {
            java.lang.Double.valueOf(double)
        } catch (e: NumberFormatException) {
            0.0
        }
    }