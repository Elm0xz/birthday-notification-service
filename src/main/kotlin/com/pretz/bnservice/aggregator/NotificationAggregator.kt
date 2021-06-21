package com.pretz.bnservice.aggregator

import com.pretz.bnservice.birthday.Notification
import com.pretz.bnservice.monthLater
import com.pretz.bnservice.weekLater
import java.time.MonthDay

class NotificationAggregator {

    fun aggregate(list: List<Notification>, date: MonthDay): AggregatedNotification {
        val map = list.filter { it.date == date }.groupBy { it.birthday.date }
        return AggregatedNotification(
            map[date] ?: emptyList(),
            map[date.weekLater()] ?: emptyList(),
            map[date.monthLater()] ?: emptyList()
        )
    }
}
