package com.pretz.bnservice.scheduler

import com.pretz.bnservice.birthday.Birthday
import com.pretz.bnservice.birthday.BirthdayRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.MonthDay

@Component
class BirthdayScheduler(
    private val repository: BirthdayRepository,
    private val emailSender: EmailSender = EmailSender()
) {

    @Scheduled(cron = "0 0 0 * * ?")
    fun schedule() {
        val birthdays = repository.findAllByDate(MonthDay.from(LocalDateTime.now()))
        emailSender.send(createNotification(birthdays))
    }

    private fun createNotification(birthdays: List<Birthday>): String =
        birthdays.fold("") { acc, it -> acc + it.name + "\n" }
}
