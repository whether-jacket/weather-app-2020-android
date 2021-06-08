package com.seljabali.core.utilities.time.localtime

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun String.parseLocalTime(format: String? = null): LocalTime? =
    if (format == null || format.isEmpty())
        LocalTime.parse(this)
    else
        LocalTime.parse(this, DateTimeFormatter.ofPattern(format))

fun LocalTime.print(format: String): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))

fun LocalTime.print(format: Any): String = this.print(format.toString())