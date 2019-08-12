package com.seljabali.core.utilities

import java.text.NumberFormat
import java.util.*

fun Int.asCommaFormatted(): String = NumberFormat.getNumberInstance(Locale.US).format(this.toLong())