package com.seljabali.core.utilities.time.zoneddatetime

import com.seljabali.core.utilities.time.localdate.parseLocalDate
import com.seljabali.core.utilities.time.localdatetime.parseLocalDateTime
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.util.Locale
import kotlin.math.roundToInt

// region Parsing
fun String.parseZonedDateTime(format: String? = null): ZonedDateTime? {
    val zonedDateTime = parseZonedDateTimeHelper(this, format)
    if (zonedDateTime != null) {
        return zonedDateTime
    }
    val localDateTime = this.parseLocalDateTime(format)
    if (localDateTime != null) {
        return ZonedDateTime.of(localDateTime, ZonedDateTimeUtil.getDefaultZoneId())
    }
    return null
}

private fun parseZonedDateTimeHelper(dateText: String, format: String?): ZonedDateTime? =
    if (format == null || format.isEmpty()) {
        try {
            ZonedDateTime.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        }
    } else {
        try {
            ZonedDateTime.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        }
    }
fun String.isMsftDate(): Boolean = this.contains("/Date(")

fun String.parseMsftDate(): ZonedDateTime {
//    "\/Date(1325134800000)\/"
    val longString = this.substring(this.indexOf("(") + 1, this.indexOf(")"))
    return ZonedDateTimeUtil.new(longString.toLong())
}

private fun doesDateTimeHaveTimeZone(dateTime: String): Boolean =
    dateTime.contains('T', ignoreCase = true)
// endregion

// region Attributes

fun ZonedDateTime.isInLeapYear(): Boolean = ZonedDateTimeUtil.isLeapYear(this.year)

fun ZonedDateTime.isAtStartOfDay(): Boolean = this.isEqualTime(this.atStartOfDay())

fun ZonedDateTime.isAtEndOfDay(): Boolean = this.isEqualTime(this.atEndOfDay())

// endregion

// region Comparisons
fun ZonedDateTime.compareDay(toDate: ZonedDateTime): Int {
    val dayDifferentFromDate = getDayDifference(toDate)
    return when {
        dayDifferentFromDate > 0 -> -1
        dayDifferentFromDate < 0 -> 1
        else -> 0
    }
}

fun ZonedDateTime.isEqualDay(b: ZonedDateTime): Boolean = compareDay(b) == 0

fun ZonedDateTime.isBeforeThanDay(b: ZonedDateTime): Boolean = compareDay(b) < 0

fun ZonedDateTime.isBeforeThanEqualDay(b: ZonedDateTime): Boolean = compareDay(b) <= 0

fun ZonedDateTime.isAfterThanDay(b: ZonedDateTime): Boolean = compareDay(b) > 0

fun ZonedDateTime.isAfterThanEqualDay(b: ZonedDateTime): Boolean = compareDay(b) >= 0

fun ZonedDateTime.compareTime(toDate: ZonedDateTime): Int =
    when {
        isEqualTime(toDate) -> 0
        isBeforeThanTime(toDate) -> -1
        else -> 1
    }

fun ZonedDateTime.isEqualTime(b: ZonedDateTime): Boolean = isEqual(b)

fun ZonedDateTime.isBeforeThanTime(b: ZonedDateTime): Boolean = this.isBefore(b)

fun ZonedDateTime.isBeforeThanEqualTime(b: ZonedDateTime): Boolean = compareTime(b) <= 0

fun ZonedDateTime.isAfterThanTime(b: ZonedDateTime): Boolean = compareTime(b) > 0

fun ZonedDateTime.isAfterThanEqualTime(b: ZonedDateTime): Boolean = compareTime(b) >= 0

fun ZonedDateTime.getMonthDifference(zonedDateTimeB: ZonedDateTime): Int {
    val yearDif = (zonedDateTimeB.year - this.year) * 12
    return yearDif + (zonedDateTimeB.month.value - this.month.value)
}

fun ZonedDateTime.areInSameYear(zonedDateTimeB: ZonedDateTime): Boolean = this.year == zonedDateTimeB.year

fun ZonedDateTime.areInSameMonth(zonedDateTimeB: ZonedDateTime): Boolean =
    year == zonedDateTimeB.year && month == zonedDateTimeB.month

fun ZonedDateTime.getDayDifference(zonedDateTimeB: ZonedDateTime): Int =
    (Duration.between(this, zonedDateTimeB).seconds.toFloat() / 60f / 60f / 24f).roundToInt()

fun ZonedDateTime.getMinuteDifference(zonedDateTimeB: ZonedDateTime): Int =
    (Duration.between(this, zonedDateTimeB).seconds / 60f).roundToInt()

fun ZonedDateTime.getHourDifference(zonedDateTimeB: ZonedDateTime): Int =
    (Duration.between(this, zonedDateTimeB).seconds / (60f * 60f)).roundToInt()
// endregion

// region Getters
fun ZonedDateTime.getMonthBaseZero(): Int = this.monthValue - 1

fun ZonedDateTime.getDaysInMonth(): Int = this.month.length(isInLeapYear())

fun ZonedDateTime.atStartOfDay(): ZonedDateTime = this.withLocalTime(LocalTime.MIN)

fun ZonedDateTime.atEndOfDay(): ZonedDateTime = this.withLocalTime(LocalTime.MAX)

fun ZonedDateTime.withLocalTime(localTime: LocalTime): ZonedDateTime {
    val withHour = this.withHour(localTime.hour)
    val withMinute = withHour.withMinute(localTime.minute)
    val withSecond = withMinute.withSecond(localTime.second)
    return withSecond.withNano(localTime.nano)
}

fun ZonedDateTime.getLast(dayOfWeek: DayOfWeek, countInCurrentDay: Boolean = false): ZonedDateTime {
    if (countInCurrentDay) {
        if (this.dayOfWeek == dayOfWeek) return this
    }
    var mostRecentDay = this
    if (mostRecentDay.dayOfWeek == dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    while (mostRecentDay.dayOfWeek != dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    return mostRecentDay
}

fun ZonedDateTime.getNext(dayOfWeek: DayOfWeek, countInCurrentDay: Boolean = false): ZonedDateTime {
    if (countInCurrentDay) {
        if (this.dayOfWeek == dayOfWeek) return this
    }
    var nextZonedDate = this
    if (nextZonedDate.dayOfWeek == dayOfWeek) {
        nextZonedDate = nextZonedDate.plusDays(1)
    }
    while (nextZonedDate.dayOfWeek != dayOfWeek) {
        nextZonedDate = nextZonedDate.plusDays(1)
    }
    return nextZonedDate
}
// endregion

// region Print
fun ZonedDateTime.print(format: String): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))

fun ZonedDateTime.print(format: Any): String = this.print(format.toString())
// endregion
