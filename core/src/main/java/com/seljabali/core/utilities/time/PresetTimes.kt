package com.seljabali.core.utilities.time

import java.time.LocalTime

fun NOW_TIME() = NOW().toLocalTime()

fun START_OF_DAY() = LocalTime.MIDNIGHT

fun END_OF_DAY() = LocalTime.MAX

fun MID_DAY() = LocalTime.NOON
