package com.seljabali.core.utilities.time

object DateTimeFormats {

    enum class DayOfWeek(override val format: String): DateTimeFormat {
        Sixth("d"),
        Mon("EEE"),
        Monday("EEEE");
    }

    enum class DayYear(override val format: String): DateTimeFormat {
        D_YYYY("d, YYYY");
    }

    enum class Month(override val format: String): DateTimeFormat {
        Jun("MMM"),
        June("MMMM"),
        SIX("MM");
    }

    enum class MonthDay(override val format: String): DateTimeFormat {
        JUL_1("MMM d"),
        JUL_01("MMM dd"),
        JULY_1("MMMM d"),
        JULY_01("MMMM dd"),
        MON_JUL_17("EEE, MMM d");
    }

    enum class YearMonthDay(override val format: String): DateTimeFormat {
        YYYYMDD("yyyyMdd"),
        YYYYMMDD("yyyyMMdd"),
        YYYY_M_DD_DASH("yyyy-M-dd"),
        YYYY_MM_DD_DASH("yyyy-MM-dd"),
        YYYY_M_DD_SLASH("yyyy/M/dd"),
        YYYY_MM_DD_SLASH("yyyy/MM/dd"),
        YYYY_M_DD_SPACE("yyyy M dd"),
        YYYY_MM_DD_SPACE("yyyy MM dd");
    }

    enum class MonthDayYear(override val format: String): DateTimeFormat {
        MMM_D_YYYY_SPACE("MMM d, yyyy"),
        MM_DD_YYYY_SLASH("MM/dd/yyyy"),
        M_D_YYYY_SLASH("M/d/yyyy");
    }

    enum class WeekdayMonthDayYear(override val format: String): DateTimeFormat {
        DAY_MMM_D_YY("EEE, MMM d, ''yy"),
        DAY_MMM_D_YYYY("EEEE, MMM d, yyyy");
    }

    enum class YearMonthDayTime(override val format: String): DateTimeFormat {
        YYYY_MM_DD_TIME_Z("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        USA_MILITARY_WITH_ZONE("M/dd/yyyy HH:mm z"),
        USA_WITH_ZONE("M/dd/yyyy h:mm a z"),
        USA("M/dd/yyyy h:mm a");
    }

    enum class Time(override val format: String): DateTimeFormat {
        // kk = Hours in 1-24 format
        // hh = hours in 1-12 format
        // KK = hours in 0-11 format
        // HH = hours in 0-23 format
        H("h"),
        HH("hh"),
        H_24("H"),
        HH_24("HH"),
        M("m"),
        MM("mm"),
        S("s"),
        SS("ss"),
        H_MM_24("H:mm"),
        HH_MM_24("HH:mm"),
        H_MM("h:mm"),
        HH_MM("hh:mm"),
        H_MM_AM("h:mm a"),
        HH_MM_AM("hh:mm a"),
        HH_MM_SS_AM("hh:mm:ss a"),
        HH_MM_SS_24("HH:mm:ss");
    }
}

interface DateTimeFormat {
    val format: String
}