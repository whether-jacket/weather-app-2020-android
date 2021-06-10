package com.seljabali.core.time

import com.seljabali.core.utilities.time.DateTimeFormats
import com.seljabali.core.utilities.time.localtime.parseLocalTime
import com.seljabali.core.utilities.time.localtime.print
import com.seljabali.core.utilities.time.zoneddatetime.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

class ZonedDateTimeTest {

    @Test
    fun `given date day month & year, when converted to date, then should match attributes`() {
        // given
        val day = 7
        val month = 6
        val year = 2021

        // when
        val dateFormed: ZonedDateTime = ZonedDateTimeUtil.new(
            year = year,
            month = month,
            day = day
        )

        // then
        assertEquals(day, dateFormed.dayOfMonth)
        assertEquals(month, dateFormed.month.value)
        assertEquals(year, dateFormed.year)
    }
    @Test
    fun `given date day month year & time, when converted to date, then should match attributes`() {
        // given
        val day = 7
        val month = 6
        val year = 2021
        val hour = 7
        val minute = 35
        val second = 45

        // when
        val dateFormed: ZonedDateTime = ZonedDateTimeUtil.new(
            year = year,
            month = month,
            day = day,
            hour = hour,
            minute = minute,
            second = second
        )

        // then
        assertEquals(day, dateFormed.dayOfMonth)
        assertEquals(month, dateFormed.month.value)
        assertEquals(year, dateFormed.year)
        assertEquals(hour, dateFormed.hour)
        assertEquals(minute, dateFormed.minute)
        assertEquals(second, dateFormed.second)
        assertEquals(0, dateFormed.nano)
    }

    @Test
    fun `given date in YYYY-MM-DD, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2021-06-07"

        // when
        val dateParsed: ZonedDateTime? = dateInText.parseZonedDateTime()

        // then
        assertNotNull(dateParsed)
        assertEquals(dateInText, dateParsed!!.print(DateTimeFormats.YearMonthDay.YYYY_MM_DD_DASH))
    }

    @Test
    fun `given date in YYYY-MM-DD, when parsed with format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2021-06-07"

        // when
        val dateParsed: ZonedDateTime? =
            dateInText.parseZonedDateTime(format = DateTimeFormats.YearMonthDay.YYYY_MM_DD_DASH.toString())

        // then
        assertNotNull(dateParsed)
        assertEquals(dateInText, dateParsed!!.print(DateTimeFormats.YearMonthDay.YYYY_MM_DD_DASH))
    }

    @Test
    fun `given date in MM-DD-YYYY, when parsed with format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "06/07/2021"

        // when
        val dateParsed: ZonedDateTime? =
            dateInText.parseZonedDateTime(format = DateTimeFormats.MonthDayYear.MM_DD_YYYY_SLASH.toString())

        // then
        assertNotNull(dateParsed)
        assertEquals(dateInText, dateParsed!!.print(DateTimeFormats.MonthDayYear.MM_DD_YYYY_SLASH))
    }

    @Test
    fun `given leap years, then should properly mark them as such`() {
        // given
        val leapYearList = arrayOf(1980, 1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032)
        var areAllLeapYears = true

        // when
        leapYearList.forEach { year ->
            val date = ZonedDateTimeUtil.new(year, 1, 1)
            areAllLeapYears = areAllLeapYears and date.isInLeapYear()
        }

        // then
        assertTrue(areAllLeapYears)
    }

    @Test
    fun `given non-leap years, then should properly mark them as such`() {
        // given
        val nonLeapYearList =
            arrayOf(1981, 1983, 1989, 1991, 1995, 2001, 2005, 2009, 2013, 2017, 2021, 2022, 2029, 2031)
        var areAnyLeapYears = false

        // when
        nonLeapYearList.forEach { year ->
            val date = ZonedDateTimeUtil.new(year, 1, 1)
            areAnyLeapYears = areAnyLeapYears or date.isInLeapYear()
        }

        // then
        assertFalse(areAnyLeapYears)
    }

    @Test
    fun `given date A & B, when A is before B, then should properly have compare results as such`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2020, 3, 20)
        val dateB = ZonedDateTimeUtil.new(2020, 3, 25)

        // when
        val isDateABeforeDayB = dateA.isBefore(dateB) && dateA.compareDay(dateB) == -1
        val isDateBAfterDayA = dateB.isAfter(dateA) && dateB.compareDay(dateA) == 1

        val isDateABeforeDayTimeB = dateA.isBeforeThanTime(dateB) && dateA.compareTime(dateB) == -1
        val isDateBAfterDayTimeA = dateB.isAfterThanTime(dateA) && dateB.compareTime(dateA) == 1

        val isDateANotEqualDayDateB = !dateA.isEqualDay(dateB) && !dateB.isEqualDay(dateA)
        val isDateANotEqualDayTimeDateB = !dateA.isEqualTime(dateB) && !dateB.isEqualTime(dateA)

        // then
        assertTrue(isDateABeforeDayB)
        assertTrue(isDateBAfterDayA)
        assertTrue(isDateABeforeDayTimeB)
        assertTrue(isDateBAfterDayTimeA)
        assertTrue(isDateANotEqualDayDateB)
        assertTrue(isDateANotEqualDayTimeDateB)
    }

    @Test
    fun `given date A & B, when A is before B, then should properly have day & month compare results as such`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2020, 3, 20)
        val dateB = ZonedDateTimeUtil.new(2020, 3, 25)

        // when
        val monthDifferenceOfAAndB = dateA.getMonthDifference(dateB)
        val monthDifferenceOfAAndBAreTheSame = dateA.getMonthDifference(dateB) == dateB.getMonthDifference(dateA)

        val dateAAndBAreInSameYear = dateA.areInSameYear(dateB) and dateB.areInSameYear(dateA)
        val dateAAndBAreInSameMonth = dateA.areInSameMonth(dateB) and dateB.areInSameMonth(dateA)

        val dateAAndBDayDifference = dateA.getDayDifference(dateB)

        // then
        assertEquals(monthDifferenceOfAAndB, 0)
        assertTrue(monthDifferenceOfAAndBAreTheSame)

        assertTrue(dateAAndBAreInSameYear)
        assertTrue(dateAAndBAreInSameMonth)

        assertEquals(dateAAndBDayDifference, 5)
    }

    @Test
    fun `given date A, then should properly describe its attributes`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2020, 6, 20)

        // when
        val monthBase0 = dateA.getMonthBaseZero()
        val getDaysInMonth = dateA.getDaysInMonth()

        // then
        assertEquals(monthBase0, 5)
        assertEquals(getDaysInMonth, 30)
    }

    @Test
    fun `given date A, when adjusted time, then should properly apply time`() {
        // given
        var dateA = ZonedDateTimeUtil.new(2020, 3, 20)
        val timeText = "07:35:11 AM"
        val time: LocalTime = timeText.parseLocalTime(DateTimeFormats.Time.HH_MM_SS_AM.toString())!!

        // when
        dateA = dateA.withLocalTime(time)
        val resultTime = dateA.toLocalTime()
        val resultText = resultTime.print(DateTimeFormats.Time.HH_MM_SS_AM)

        // then
        assertEquals(time, resultTime)
        assertEquals(timeText, resultText)
    }

    @Test
    fun `given date is on a Monday, when looking for last Monday inclusive, then should return same date`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2021, 6, 7)

        // when
        val resultLastMondayInclusive = dateA.getLastIncludingToday(DayOfWeek.MONDAY)

        // then
        assertTrue(dateA.isEqualDay(resultLastMondayInclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for last Monday exclusive, then should return date minus 1 week`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2021, 6, 14)
        val expectedDate = ZonedDateTimeUtil.new(2021, 6, 7)

        // when
        val resultLastMondayExclusive = dateA.getLast(DayOfWeek.MONDAY)

        // then
        assertTrue(expectedDate.isEqualDay(resultLastMondayExclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for next Monday inclusive, then should return same date`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2021, 6, 7)

        // when
        val resultNextMondayInclusive = dateA.getNextIncludingToday(DayOfWeek.MONDAY)

        // then
        assertTrue(dateA.isEqualDay(resultNextMondayInclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for next Monday exclusive, then should return date plus 1 week`() {
        // given
        val dateA = ZonedDateTimeUtil.new(2021, 6, 7)
        val expectedDate = ZonedDateTimeUtil.new(2021, 6, 14)

        // when
        val resultNextMondayExclusive = dateA.getNext(DayOfWeek.MONDAY)

        // then
        assertTrue(expectedDate.isEqualDay(resultNextMondayExclusive))
    }

    @Test
    fun `given 2 dateTimes 2 and half hrs apart, then should see such differences in comparing`() {
        // given
        // 2021-06-08 3:30 PM
        val dateA = ZonedDateTimeUtil.new(
            year = 2021,
            month = 6,
            day = 8,
            hour = 15,
            minute = 30,
            second = 0

        )
        // 2021-06-08 4:40 PM
        val dateB = ZonedDateTimeUtil.new(
            year = 2021,
            month = 6,
            day = 8,
            hour = 16,
            minute = 40,
            second = 0
        )

        // when
        val expectedDayDifference = dateA.getDayDifference(dateB)
        val expectedHourDifference = dateA.getHourDifference(dateB)
        val expectedMinuteDifference = dateA.getMinuteDifference(dateB)

        // then
        assertEquals(expectedDayDifference, 0)
        assertEquals(expectedHourDifference, 1)
        assertEquals(expectedMinuteDifference, 70)
    }
}