package com.seljabali.templateapplication.utilities

import java.text.NumberFormat
import java.util.*

fun Int.asAmountCommaFormatted(): String = NumberFormat.getNumberInstance(Locale.US).format(this.toLong())