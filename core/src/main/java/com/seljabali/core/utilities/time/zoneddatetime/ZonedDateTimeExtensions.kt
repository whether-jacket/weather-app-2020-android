package com.seljabali.core.utilities.time.zoneddatetime

import com.seljabali.core.utilities.time.DateTimeFormats
import com.seljabali.core.utilities.time.localdate.parseLocalDate
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
fun String.parseZonedDate(format: String? = null): ZonedDateTime? {
    var result = parseZonedDateHelper(this, format)
    if (format != null && format.isEmpty()) {
        return result
    }
    for (presetFormat in DateTimeFormats.yearMonthDayFormats) {
        result = parseZonedDateHelper(this, presetFormat)
        if (result != null) {
            return result
        }
    }
    val localDate = this.parseLocalDate(format) ?: return null
    return ZonedDateTime.of(localDate, LocalTime.now(), ZonedDateTimeUtil.getDefaultZoneId())
}

private fun parseZonedDateHelper(dateText: String, format: String?): ZonedDateTime? =
    if (format == null || format.isEmpty())
        try {
            ZonedDateTime.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        }
    else {
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
// endregion

// region Attributes

fun ZonedDateTime.isInLeapYear(): Boolean = ZonedDateTimeUtil.isLeapYear(this.year)

fun ZonedDateTime.isAtStartOfDay(): Boolean = this.isEqualsTime(this.atStartOfDay())

fun ZonedDateTime.isAtEndOfDay(): Boolean = this.isEqualsTime(this.atEndOfDay())

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

fun ZonedDateTime.isBeforeThanEqualsDay(b: ZonedDateTime): Boolean = compareDay(b) <= 0

fun ZonedDateTime.isAfterThanDay(b: ZonedDateTime): Boolean = compareDay(b) > 0

fun ZonedDateTime.isAfterThanEqualsDay(b: ZonedDateTime): Boolean = compareDay(b) >= 0

fun ZonedDateTime.compareTime(toDate: ZonedDateTime): Int =
    when {
        isEqualsTime(toDate) -> 0
        isBeforeThanTime(toDate) -> -1
        else -> 1
    }

fun ZonedDateTime.isEqualsTime(b: ZonedDateTime): Boolean = isEqual(b)

fun ZonedDateTime.isBeforeThanTime(b: ZonedDateTime): Boolean = this.isBefore(b)

fun ZonedDateTime.isBeforeThanEqualsTime(b: ZonedDateTime): Boolean = compareTime(b) <= 0

fun ZonedDateTime.isAfterThanTime(b: ZonedDateTime): Boolean = compareTime(b) > 0

fun ZonedDateTime.isAfterThanEqualsTime(b: ZonedDateTime): Boolean = compareTime(b) >= 0

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

fun ZonedDateTime.atStartOfDay(): ZonedDateTime = this.toLocalDate().atStartOfDay(this.zone)

fun ZonedDateTime.atEndOfDay(): ZonedDateTime = this.toLocalDate().atTime(LocalTime.MAX).atZone(this.zone)

fun ZonedDateTime.withLocalTime(localTime: LocalTime): ZonedDateTime {
    val withHour = this.withHour(localTime.hour)
    val withMinute = withHour.withMinute(localTime.minute)
    return withMinute.withSecond(localTime.second)
}

fun ZonedDateTime.getLastIncludingToday(dayOfWeek: DayOfWeek): ZonedDateTime =
    if (this.dayOfWeek == dayOfWeek) this else getLast(dayOfWeek)

fun ZonedDateTime.getLast(dayOfWeek: DayOfWeek): ZonedDateTime {
    var mostRecentDay = this
    if (mostRecentDay.dayOfWeek == dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    while (mostRecentDay.dayOfWeek != dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    return mostRecentDay
}

fun ZonedDateTime.getNextIncludingToday(dayOfWeek: DayOfWeek): ZonedDateTime =
    if (this.dayOfWeek == dayOfWeek) this else getNext(dayOfWeek)

fun ZonedDateTime.getNext(dayOfWeek: DayOfWeek): ZonedDateTime {
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