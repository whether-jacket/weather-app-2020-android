package com.seljabali.templateapplication.utilities.threeten

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException

fun String.parseLocalDate(): LocalDate? {
    var result: LocalDate? = try {
        LocalDate.parse(this)
    } catch (e: Throwable) {
        null
    }
    if (result != null) {
        return result
    }
    for (format in Formats.yearMonthDayFormats) {
        try {
            val formatter = DateTimeFormatter.ofPattern(format)
            result = LocalDate.parse(this, formatter)
            if (result != null) {
                return result
            }
        } catch (e: DateTimeParseException) {
            // do nothing
        }
    }
    return null
}

fun String.parseLocalDateTime(): LocalDateTime? {
    var result: LocalDateTime? =
        try {
            LocalDateTime.parse(this)
        } catch (e: DateTimeParseException) {
            null
        }
    if (result != null) {
        return result
    }
    for (format in Formats.yearMonthDayFormats) {
        try {
            val formatter = DateTimeFormatter.ofPattern(format)
            result = LocalDateTime.parse(this, formatter)
            if (result != null) {
                return result
            }
        } catch (e: DateTimeParseException) {
            // do nothing
        }
    }
    return null
}
