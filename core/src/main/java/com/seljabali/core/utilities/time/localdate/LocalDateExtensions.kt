package com.seljabali.core.utilities.time.localdate

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.util.Locale

fun String.parseLocalDate(format: String? = null): LocalDate? =
    if (format == null || format.isEmpty()) {
        try {
            LocalDate.parse(this)
        } catch (e: DateTimeParseException) {
            null
        }
    } else {
        try {
            LocalDate.parse(this, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        }
    }

fun LocalDate.print(format: String): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))