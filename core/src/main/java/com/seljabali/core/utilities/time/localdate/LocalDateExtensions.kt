package com.seljabali.core.utilities.time.localdate

import com.seljabali.core.utilities.time.DateTimeFormats
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.parseLocalDate(format: String? = null): LocalDate? {
    var result = parseLocalDateHelper(this, format)
    if (format != null && format.isNotEmpty()) {
        return result
    }
    for (presetFormat in DateTimeFormats.yearMonthDayFormats) {
        result = parseLocalDateHelper(this, presetFormat)
        if (result != null) {
            return result
        }
    }
    return null
}

private fun parseLocalDateHelper(dateText: String, format: String?): LocalDate? =
    if (format == null || format.isEmpty())
        try {
            LocalDate.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        }
    else {
        try {
            LocalDate.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        }
    }