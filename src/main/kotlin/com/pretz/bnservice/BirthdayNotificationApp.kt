package com.pretz.bnservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BirthdayNotificationApp {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BirthdayNotificationApp>(*args)
        }
    }
}
