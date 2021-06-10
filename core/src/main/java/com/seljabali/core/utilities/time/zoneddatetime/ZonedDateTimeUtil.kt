package com.seljabali.core.utilities.time.zoneddatetime

import java.time.DateTimeException
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.zone.ZoneRulesException
import java.util.Calendar

object ZonedDateTimeUtil {

    fun getDefaultZoneId(): ZoneId =
        try {
            ZoneId.systemDefault()
        } catch (e: DateTimeException) {
            null
        } catch (e: ZoneRulesException) {
            null
        } ?: ZoneId.of("America/Montreal")

    fun new(year: Int, month: Int, day: Int): ZonedDateTime {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return new(calendar.timeInMillis)
    }

    fun new(
        year: Int = 0,
        month: Int = 0,
        day: Int = 0,
        hour: Int = 0,
        minute: Int = 0,
        second: Int = 0
    ): ZonedDateTime {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day, hour, minute, second)
        calendar.set(Calendar.MILLISECOND, 0)
        return new(calendar.timeInMillis)
    }

    fun new(millis: Long): ZonedDateTime = Instant.ofEpochMilli(millis).atZone(getDefaultZoneId())

    fun isLeapYear(year: Int): Boolean =
        when {
            year % 4 != 0 -> false
            year % 400 == 0 -> true
            year % 100 == 0 -> false
            else -> true
        }
}