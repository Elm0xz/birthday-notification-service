package com.pretz.bnservice

import java.time.LocalDate
import java.time.MonthDay
import java.time.Year


fun MonthDay.weekEarlier(): MonthDay {
    val fullDate = LocalDate.from(Year.now().atMonthDay(this)).minusWeeks(1)
    return MonthDay.from(fullDate)
}

fun MonthDay.monthEarlier(): MonthDay {
    val fullDate = LocalDate.from(Year.now().atMonthDay(this)).minusMonths(1)
    return MonthDay.from(fullDate)
}

fun MonthDay.weekLater(): MonthDay {
    val fullDate = LocalDate.from(Year.now().atMonthDay(this)).plusWeeks(1)
    return MonthDay.from(fullDate)
}

fun MonthDay.monthLater(): MonthDay {
    val fullDate = LocalDate.from(Year.now().atMonthDay(this)).plusMonths(1)
    return MonthDay.from(fullDate)
}