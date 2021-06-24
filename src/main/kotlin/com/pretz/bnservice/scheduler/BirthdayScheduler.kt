package com.pretz.bnservice.scheduler

import com.pretz.bnservice.aggregator.NotificationAggregator
import com.pretz.bnservice.birthday.NotificationRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.MonthDay

@Component
class BirthdayScheduler(
    private val repository: NotificationRepository,
    private val notificationAggregator: NotificationAggregator,
    private val emailSender: EmailSender = EmailSender()
) {

    @Scheduled(cron = "0 0 0 * * ?")
    fun schedule() {
        val date = MonthDay.from(LocalDateTime.now())
        val notifications = repository.findAllByDate(date)
        emailSender.send(notificationAggregator.aggregate(notifications, date))
    }
}
