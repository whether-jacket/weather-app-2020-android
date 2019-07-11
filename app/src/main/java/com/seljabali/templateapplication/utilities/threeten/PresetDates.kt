package com.seljabali.templateapplication.utilities.threeten

import org.threeten.bp.DayOfWeek
import org.threeten.bp.ZonedDateTime

fun NOW(): ZonedDateTime = ZonedDateTime.now(ZonedDateTimeUtil.getDefaultZoneId())
fun YESTERDAY(): ZonedDateTime = NOW().minusDays(1)
fun TOMORROW(): ZonedDateTime = NOW().plusDays(1) 

fun LAST_MONDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.MONDAY) 
fun LAST_TUESDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.TUESDAY) 
fun LAST_WEDNESDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.WEDNESDAY) 
fun LAST_THURSDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.THURSDAY) 
fun LAST_FRIDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.FRIDAY) 
fun LAST_SATURDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.SATURDAY) 
fun LAST_SUNDAY(): ZonedDateTime = NOW().getLast(DayOfWeek.SUNDAY) 

fun NEXT_MONDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.MONDAY) 
fun NEXT_TUESDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.TUESDAY) 
fun NEXT_WEDNESDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.WEDNESDAY) 
fun NEXT_THURSDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.THURSDAY) 
fun NEXT_FRIDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.FRIDAY) 
fun NEXT_SATURDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.SATURDAY) 
fun NEXT_SUNDAY(): ZonedDateTime = NOW().getNext(DayOfWeek.SUNDAY) 