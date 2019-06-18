package com.seljabali.templateapplication.utilities.threeten

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.format.DateTimeParseException
import java.util.*

class ZonedDateTimeUtil {

    companion object {
        fun getDefaultZoneId(): ZoneId =
            try {
                ZoneId.systemDefault()
            } catch (e: Exception) {
                null
            } ?: ZoneId.of("America/Montreal")

        fun new(year: Int, month: Int, day: Int): ZonedDateTime {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return new(calendar.timeInMillis)
        }

        fun new(millis: Long): ZonedDateTime = Instant.ofEpochMilli(millis).atZone(getDefaultZoneId())

        fun isLeapYear(year: Int): Boolean = when {
            year % 4 != 0 -> false
            year % 400 == 0 -> true
            year % 100 == 0 -> false
            else -> true
        }
    }
}

/***********
 * PARSERS *
 ***********/

fun String.parseZonedDate(): ZonedDateTime? = this.parseZonedDate("")

fun String.parseZonedDate(format: String?): ZonedDateTime? {
    val result = if (format == null || format.isEmpty())
        parseZonedDateHelper(this)
    else
        parseZonedDateHelper(this, format)
    if (result != null) {
        return result
    }
    val localDate = this.parseLocalDate() ?: return null
    return ZonedDateTime.of(localDate, LocalTime.now(), ZonedDateTimeUtil.getDefaultZoneId())
}

private fun parseZonedDateHelper(dateText: String): ZonedDateTime? = parseZonedDateHelper(dateText, "")

private fun parseZonedDateHelper(dateText: String, format: String?): ZonedDateTime? {
    var result: ZonedDateTime? = try {
        if (format == null || format.isEmpty()) {
            ZonedDateTime.parse(dateText)
        } else {
            ZonedDateTime.parse(dateText, DateTimeFormatter.ofPattern(format))
        }
    } catch (e: DateTimeParseException) {
        null
    }
    if (result != null) {
        return result
    }
    for (presetFormat in Formats.yearMonthDayFormats) {
        try {
            val formatter = DateTimeFormatter.ofPattern(presetFormat)
            result = ZonedDateTime.parse(dateText, formatter)
            if (result != null) {
                return result
            }
        } catch (e: DateTimeParseException) {
            // do nothing
        }
    }
    return null
}

/***************
 * COMPARISONS *
 ***************/

fun ZonedDateTime.compareDay(toDate: ZonedDateTime): Int {
    val dayDifferentFromDate = getDayDifference(toDate)
    return when {
        dayDifferentFromDate > 0 -> -1
        dayDifferentFromDate < 0 -> 1
        else -> 0
    }
}

fun ZonedDateTime.equalsDay(b: ZonedDateTime): Boolean = compareDay(b) == 0

fun ZonedDateTime.beforeThanDay(b: ZonedDateTime): Boolean = compareDay(b) < 0

fun ZonedDateTime.beforeThanEqualsDay(b: ZonedDateTime): Boolean = compareDay(b) <= 0

fun ZonedDateTime.afterThanDay(b: ZonedDateTime): Boolean = compareDay(b) > 0

fun ZonedDateTime.afterThanEqualsDay(b: ZonedDateTime): Boolean = compareDay(b) >= 0

fun ZonedDateTime.compareTime(toDate: ZonedDateTime): Int =
    when {
        equalsTime(toDate) -> 0
        beforeThanTime(toDate) -> -1
        else -> 1
    }

fun ZonedDateTime.equalsTime(b: ZonedDateTime): Boolean = isEqual(b)

fun ZonedDateTime.beforeThanTime(b: ZonedDateTime): Boolean = this.isBefore(b)

fun ZonedDateTime.beforeThanEqualsTime(b: ZonedDateTime): Boolean = (this.isBefore(b) || this.isEqual(b))

fun ZonedDateTime.afterThanTime(b: ZonedDateTime): Boolean = this.isAfter(b)

fun ZonedDateTime.afterThanEqualsTime(b: ZonedDateTime): Boolean = (this.isAfter(b) || this.isEqual(b))

fun ZonedDateTime.getMonthDifference(zonedDateTimeB: ZonedDateTime): Int {
    val yearDif = (zonedDateTimeB.year - this.year) * 12
    return yearDif + (zonedDateTimeB.month.value - this.month.value)
}

fun ZonedDateTime.areInSameYear(zonedDateTimeB: ZonedDateTime): Boolean = this.year == zonedDateTimeB.year

fun ZonedDateTime.areInSameMonth(zonedDateTimeB: ZonedDateTime): Boolean =
    year == zonedDateTimeB.year && month == zonedDateTimeB.month

fun ZonedDateTime.getDayDifference(zonedDateTimeB: ZonedDateTime): Int =
    Math.round(Duration.between(this, zonedDateTimeB).seconds.toFloat() / 60f / 60f / 24f)

fun ZonedDateTime.getMinuteDifference(zonedDateTimeB: ZonedDateTime): Int =
    Math.round(Duration.between(this, zonedDateTimeB).seconds / 60f)

fun ZonedDateTime.getHourDifference(zonedDateTimeB: ZonedDateTime): Int =
    Math.round(Duration.between(this, zonedDateTimeB).seconds / (60f * 60f))

/***********
 * GETTERS *
 ***********/

fun ZonedDateTime.getMonthBaseZero(): Int = this.monthValue - 1

fun ZonedDateTime.toWithoutTimePrecision(): ZonedDateTime = printZonedDateTime(Formats.YearMonthDay.YYYY_MM_DD_DASH).parseZonedDate()!!

fun ZonedDateTime.getDaysInMonth(zonedDateTimeA: ZonedDateTime): Int = zonedDateTimeA.month.length(isLeapYear())

fun ZonedDateTime.isLeapYear(): Boolean = ZonedDateTimeUtil.isLeapYear(this.year)

/*********
 * PRINT *
 *********/

fun ZonedDateTime.printZonedDateTime(format: String): String =
    this.format(
        DateTimeFormatterBuilder()
            .appendPattern(format)
            .toFormatter(Locale.US)
    )

fun ZonedDateTime.printZonedDateTime(format: Any): String = this.printZonedDateTime(format.toString())

