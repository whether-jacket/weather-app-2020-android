package com.seljabali.core.utilities.time.localdatetime

import com.seljabali.core.utilities.time.DateTimeFormats
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.parseLocalDateTime(format: String? = null): LocalDateTime? {
    var result = parseLocalDateTimeHelper(this, format)
    if (format != null && format.isNotEmpty()) {
        return result
    }
    for (presetFormat in DateTimeFormats.yearMonthDayFormats) {
        result = parseLocalDateTimeHelper(this, presetFormat)
        if (result != null) {
            return result
        }
    }
    return null
}

private fun parseLocalDateTimeHelper(dateText: String, format: String?): LocalDateTime? =
    if (format == null || format.isEmpty())
        try {
            LocalDateTime.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        }
    else {
        try {
            LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        }
    }