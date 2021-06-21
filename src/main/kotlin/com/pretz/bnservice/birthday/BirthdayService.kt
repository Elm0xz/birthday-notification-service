package com.pretz.bnservice.birthday

import org.springframework.stereotype.Service

@Service
class BirthdayService(val birthdayRepository: BirthdayRepository, val notificationRepository: NotificationRepository) {

    fun add(birthday: Birthday) {
        val savedBirthday = birthdayRepository.save(birthday)
        when (savedBirthday.notificationMode) {
            EarlierNotificationMode.NONE -> notificationRepository.save(Notification.from(savedBirthday))
            EarlierNotificationMode.WEEK -> saveWithWeekEarlier(savedBirthday)
            EarlierNotificationMode.MONTH -> saveWithMonthEarlier(savedBirthday)
            EarlierNotificationMode.WEEK_MONTH -> saveWithWeekAndMonthEarlier(savedBirthday)
        }
    }

    private fun saveWithWeekEarlier(birthday: Birthday) {
        notificationRepository.saveAll(
            setOf(
                Notification.from(birthday),
                Notification.weekEarlierfrom(birthday)
            )
        )
    }

    private fun saveWithMonthEarlier(birthday: Birthday) {
        notificationRepository.saveAll(
            setOf(
                Notification.from(birthday),
                Notification.monthEarlierfrom(birthday)
            )
        )
    }

    private fun saveWithWeekAndMonthEarlier(birthday: Birthday) {
        notificationRepository.saveAll(
            setOf(
                Notification.from(birthday),
                Notification.weekEarlierfrom(birthday),
                Notification.monthEarlierfrom(birthday)
            )
        )
    }

    fun delete(birthday: Birthday) {
        birthdayRepository.delete(birthday)
        val notificationsToDelete = notificationRepository.findAllByBirthday(birthday)
        notificationRepository.deleteAll(notificationsToDelete)
    }
}
