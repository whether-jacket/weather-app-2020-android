package com.seljabali.templateapplication.utilities.threeten

import java.util.ArrayList

object Formats {

    val yearMonthDayFormats: List<String> = ArrayList<String>().apply {
        for (yearMonthDay in YearMonthDay.values()) {
            add(yearMonthDay.toString())
        }
        for (yearMonthDayTime in YearMonthDayTime.values()) {
            add(yearMonthDayTime.toString())
        }
    }

    enum class DayOfWeek(private val text: String) {
        Sixth("d"),
        Mon("EEE"),
        Monday("EEEE");

        override fun toString(): String = text
    }

    enum class DayYear(private val text: String) {
        D_YYYY("d, YYYY");

        override fun toString(): String = text
    }

    enum class Month(private val text: String) {
        Jun("MMM"),
        June("MMMM"),
        SIX(""); // TODO

        override fun toString(): String = text
    }

    enum class MonthDay(private val text: String) {
        JUL_1("MMM d"),
        JUL_01("MMM dd"),
        JULY_1("MMMM d"),
        JULY_01("MMMM dd"),
        MON_JUL_17("EEE, MMM d");

        override fun toString(): String = text
    }

    enum class YearMonthDay(private val text: String) {
        YYYYMDD("yyyyMdd"),
        YYYYMMDD("yyyyMMdd"),
        YYYY_M_DD_DASH("yyyy-M-dd"),
        YYYY_MM_DD_DASH("yyyy-MM-dd"),
        YYYY_M_DD_SLASH("yyyy/M/dd"),
        YYYY_MM_DD_SLASH("yyyy/MM/dd"),
        YYYY_M_DD_SPACE("yyyy M dd"),
        YYYY_MM_DD_SPACE("yyyy MM dd"),
        MMM_D_YYYY_SPACE("MMM d, yyyy"),
        MM_DD_YYYY_SLASH("MM/dd/yyyy"),
        M_D_YYYY_SLASH("M/d/yyyy"),
        DAY_MMM_D_YY("EEE, MMM d, ''yy"),
        DAY_MMM_D_YYYY("EEEE, MMM d, yyyy");

        override fun toString(): String = text
    }

    enum class YearMonthDayTime(private val text: String) {
        YYYY_MM_DD_TIME_Z("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        USA_WITH_ZONE("M/dd/yyyy h:mm a z"),
        USA("M/dd/yyyy h:mm a");

        override fun toString(): String = text
    }

    enum class Time(private val text: String) {
        HH("h"),
        MM("mm"),
        HH_MM("h:mm"),
        HH_MM_AM("h:mm a"),
        HH_MM_AM_ALT("h:mma"),
        HH_MM_AM_ALT2("ha"),
        HH_MM_AM_ALT3("hha"),
        H_24("k");

        override fun toString(): String = text
    }
}
