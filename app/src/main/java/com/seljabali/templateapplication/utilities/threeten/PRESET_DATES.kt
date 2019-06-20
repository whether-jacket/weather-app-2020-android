package com.seljabali.templateapplication.utilities.threeten

import org.threeten.bp.DayOfWeek
import org.threeten.bp.ZonedDateTime

val NOW: ZonedDateTime by lazy { ZonedDateTime.now(ZonedDateTimeUtil.getDefaultZoneId()) }
val YESTERDAY: ZonedDateTime  by lazy { NOW.minusDays(1) }
val TOMORROW by lazy { NOW.plusDays(1) }

val LAST_MONDAY by lazy { NOW.getLast(DayOfWeek.MONDAY) }
val LAST_TUESDAY by lazy { NOW.getLast(DayOfWeek.TUESDAY) }
val LAST_WEDNESDAY by lazy { NOW.getLast(DayOfWeek.WEDNESDAY) }
val LAST_THURSDAY by lazy { NOW.getLast(DayOfWeek.THURSDAY) }
val LAST_FRIDAY by lazy { NOW.getLast(DayOfWeek.FRIDAY) }
val LAST_SATURDAY by lazy { NOW.getLast(DayOfWeek.SATURDAY) }
val LAST_SUNDAY by lazy { NOW.getLast(DayOfWeek.SUNDAY) }

val NEXT_MONDAY by lazy { NOW.getNext(DayOfWeek.MONDAY) }
val NEXT_TUESDAY by lazy { NOW.getNext(DayOfWeek.TUESDAY) }
val NEXT_WEDNESDAY by lazy { NOW.getNext(DayOfWeek.WEDNESDAY) }
val NEXT_THURSDAY by lazy { NOW.getNext(DayOfWeek.THURSDAY) }
val NEXT_FRIDAY by lazy { NOW.getNext(DayOfWeek.FRIDAY) }
val NEXT_SATURDAY by lazy { NOW.getNext(DayOfWeek.SATURDAY) }
val NEXT_SUNDAY by lazy { NOW.getNext(DayOfWeek.SUNDAY) }