package com.pretz.bnservice.birthday

import com.pretz.bnservice.monthEarlier
import com.pretz.bnservice.weekEarlier
import java.time.MonthDay

data class Notification(val birthday: Birthday, val date: MonthDay) {

    companion object {
        internal fun from(birthday: Birthday) = Notification(birthday, birthday.date)
        internal fun weekEarlierfrom(birthday: Birthday) = Notification(birthday, birthday.date.weekEarlier())
        internal fun monthEarlierfrom(birthday: Birthday) = Notification(birthday, birthday.date.monthEarlier())
    }
}
