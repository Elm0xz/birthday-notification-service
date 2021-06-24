package com.pretz.bnservice.scheduler

import com.pretz.bnservice.aggregator.AggregatedNotification
import org.springframework.stereotype.Component

@Component
class EmailSender {
    fun send(notification: AggregatedNotification) {
        //TODO implement
        println("Sending email! $notification")
    }
}
