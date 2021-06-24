package com.pretz.bnservice.aggregator

import com.pretz.bnservice.birthday.Notification
import com.pretz.bnservice.monthLater
import com.pretz.bnservice.weekLater
import org.springframework.stereotype.Component
import java.time.MonthDay

@Component
class NotificationAggregator {

    fun aggregate(notification: Iterable<Notification>, date: MonthDay): AggregatedNotification {
        val map = notification.filter { it.date == date }.groupBy { it.birthday.date }
        return AggregatedNotification(
            map[date] ?: emptyList(),
            map[date.weekLater()] ?: emptyList(),
            map[date.monthLater()] ?: emptyList()
        )
    }
}
