package com.pretz.bnservice.birthday

import org.springframework.data.repository.CrudRepository
import java.time.MonthDay

interface NotificationRepository: CrudRepository<Notification, Long> {

    fun findAllByBirthday(birthday: Birthday): Iterable<Notification>
    fun findAllByDate(monthDay: MonthDay): Iterable<Notification>
}
