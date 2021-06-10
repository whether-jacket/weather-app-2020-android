package com.seljabali.core.utilities.time.localtime

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.util.Locale

fun String.parseLocalTime(format: String? = null): LocalTime? =
    if (format == null || format.isEmpty()) {
        try {
            LocalTime.parse(this)
        } catch (e: DateTimeParseException) {
            null
        }
    } else {
        try {
            LocalTime.parse(this, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        }
    }

fun LocalTime.print(format: String): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))

fun LocalTime.print(format: Any): String = this.print(format.toString())