package com.pretz.bnservice.birthday

import org.springframework.data.repository.CrudRepository

interface NotificationRepository: CrudRepository<Notification, Long> {

    fun findAllByBirthday(birthday: Birthday): Iterable<Notification>
}