package com.seljabali.core.utilities.time

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun String.parseLocalTime(): LocalTime? = LocalTime.parse(this)

fun String.parseLocalTime(format: String): LocalTime? = LocalTime.parse(this, DateTimeFormatter.ofPattern(format))

fun LocalTime.print(format: String = Formats.Time.H_MM_AM.toString()): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))

fun LocalTime.print(format: Any): String = this.print(format.toString())

