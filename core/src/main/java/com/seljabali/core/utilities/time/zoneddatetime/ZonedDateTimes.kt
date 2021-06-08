package com.seljabali.core.utilities.time.zoneddatetime

import java.time.DayOfWeek
import java.time.ZonedDateTime

object ZonedDateTimes {

    fun now(): ZonedDateTime = ZonedDateTime.now(ZonedDateTimeUtil.getDefaultZoneId())
    fun yesterday(): ZonedDateTime = now().minusDays(1)
    fun tomorrow(): ZonedDateTime = now().plusDays(1)

    fun lastMonday(): ZonedDateTime = now().getLast(DayOfWeek.MONDAY)
    fun lastTuesday(): ZonedDateTime = now().getLast(DayOfWeek.TUESDAY)
    fun lastWednesday(): ZonedDateTime = now().getLast(DayOfWeek.WEDNESDAY)
    fun lastThursday(): ZonedDateTime = now().getLast(DayOfWeek.THURSDAY)
    fun lastFriday(): ZonedDateTime = now().getLast(DayOfWeek.FRIDAY)
    fun lastSaturday(): ZonedDateTime = now().getLast(DayOfWeek.SATURDAY)
    fun lastSunday(): ZonedDateTime = now().getLast(DayOfWeek.SUNDAY)

    fun nextMonday(): ZonedDateTime = now().getNext(DayOfWeek.MONDAY)
    fun nextTuesday(): ZonedDateTime = now().getNext(DayOfWeek.TUESDAY)
    fun nextWednesday(): ZonedDateTime = now().getNext(DayOfWeek.WEDNESDAY)
    fun nextThursday(): ZonedDateTime = now().getNext(DayOfWeek.THURSDAY)
    fun nextFriday(): ZonedDateTime = now().getNext(DayOfWeek.FRIDAY)
    fun nextSaturday(): ZonedDateTime = now().getNext(DayOfWeek.SATURDAY)
    fun nextSunday(): ZonedDateTime = now().getNext(DayOfWeek.SUNDAY)
}