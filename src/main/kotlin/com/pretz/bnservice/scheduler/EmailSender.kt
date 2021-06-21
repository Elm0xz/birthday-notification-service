package com.pretz.bnservice.scheduler

import org.springframework.stereotype.Component

@Component
class EmailSender {
    fun send(notification: String) {
        //TODO implement
        println("Sending email! $notification")
    }
}
