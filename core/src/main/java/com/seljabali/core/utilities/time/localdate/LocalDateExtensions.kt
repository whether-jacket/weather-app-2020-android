package com.seljabali.core.utilities.time.localdate

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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