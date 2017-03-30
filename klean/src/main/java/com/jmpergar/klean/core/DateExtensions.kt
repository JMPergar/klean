package com.jmpergar.klean.core

import java.text.SimpleDateFormat
import java.util.*

fun Date.isToday(): Boolean = compareWithTodayResultIs(this, 0)

fun Date.isTomorrow(): Boolean = when (day - getToday().day) { 1 -> true; else -> false }

fun Date.isPastDay(): Boolean = compareWithTodayResultIs(this, -1)

fun Date.isFutureDay(): Boolean = compareWithTodayResultIs(this, 1)

fun Date.getFormattedText(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(this)
}

private fun compareWithTodayResultIs(date: Date, value: Int): Boolean {
    val today = getToday()
    val dayDate = getDayDate(date)
    return when (dayDate.time.compareTo(today.time)) { value -> true; else -> false }
}

private fun getToday(): Date {
    val c = Calendar.getInstance()
    c.set(Calendar.HOUR_OF_DAY, 0)
    c.set(Calendar.MINUTE, 0)
    c.set(Calendar.SECOND, 0)
    c.set(Calendar.MILLISECOND, 0)
    return c.time
}

private fun getDayDate(date: Date): Date {
    val c = GregorianCalendar()
    c.time = date
    c.set(Calendar.HOUR_OF_DAY, 0)
    c.set(Calendar.MINUTE, 0)
    c.set(Calendar.SECOND, 0)
    c.set(Calendar.MILLISECOND, 0)
    return c.time
}
