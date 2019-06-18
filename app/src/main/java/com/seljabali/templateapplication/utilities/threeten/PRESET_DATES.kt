package com.seljabali.templateapplication.utilities.threeten

import org.threeten.bp.ZonedDateTime

val NOW: ZonedDateTime by lazy { ZonedDateTime.now(ZonedDateTimeUtil.getDefaultZoneId()) }

val YESTERDAY: ZonedDateTime  by lazy { NOW.minusDays(1) }

val TOMORROW by lazy { NOW.plusDays(1) }