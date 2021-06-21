package com.pretz.bnservice.aggregator

import com.pretz.bnservice.birthday.Birthday
import com.pretz.bnservice.birthday.EarlierNotificationMode
import com.pretz.bnservice.birthday.Notification
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.MonthDay

class NotificationAggregatorTest {

    private val notificationAggregator = NotificationAggregator()

    @Test
    fun `should aggregate two birthday date notifications into one list`() {
        val date = MonthDay.of(5, 8)
        val firstBirthday = Birthday("Edward Gibbon", date)
        val secondBirthday = Birthday("Harry S. Truman", date)
        val firstBirthdayDateNotification = Notification(firstBirthday, date)
        val secondBirthdayDateNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(firstBirthdayDateNotification, secondBirthdayDateNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                listOf(firstBirthdayDateNotification, secondBirthdayDateNotification),
                emptyList(),
                emptyList()
            )
        )
    }

    @Test
    fun `should aggregate birthday date notification and week earlier notification into separate lists`() {
        val date = MonthDay.of(5, 29)
        val firstBirthday = Birthday("Peter Higgs", date)
        val secondBirthday = Birthday("Someone", MonthDay.of(6, 5), EarlierNotificationMode.WEEK)
        val birthdayDateNotification = Notification(firstBirthday, date)
        val weekEarlierNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(birthdayDateNotification, weekEarlierNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                listOf(birthdayDateNotification),
                listOf(weekEarlierNotification),
                emptyList()
            )
        )
    }

    @Test
    fun `should aggregate birthday date notification and month earlier notification into separate lists`() {
        val date = MonthDay.of(2, 8)
        val firstBirthday = Birthday("Daniel Bernoulli", date)
        val secondBirthday = Birthday("Ignacy Łukasiewicz", MonthDay.of(3, 8), EarlierNotificationMode.MONTH)
        val onBirthdayNotification = Notification(firstBirthday, date)
        val monthEarlierNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(onBirthdayNotification, monthEarlierNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                listOf(onBirthdayNotification),
                emptyList(),
                listOf(monthEarlierNotification)
            )
        )
    }

    @Test
    fun `should aggregate week earlier notification and month earlier notification into separate lists`() {
        val date = MonthDay.of(2, 20)
        val firstBirthday = Birthday("John Steinbeck", MonthDay.of(2, 27), EarlierNotificationMode.WEEK)
        val secondBirthday = Birthday("Someone", MonthDay.of(3, 20), EarlierNotificationMode.MONTH)
        val weekEarlierNotification = Notification(firstBirthday, date)
        val monthEarlierNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(weekEarlierNotification, monthEarlierNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                emptyList(),
                listOf(weekEarlierNotification),
                listOf(monthEarlierNotification)
            )
        )
    }

    @Test
    fun `should aggregate two week earlier notifications into one list`() {
        val date = MonthDay.of(8, 10)
        val firstBirthday = Birthday("Jan III Sobieski", MonthDay.of(8, 17), EarlierNotificationMode.WEEK)
        val secondBirthday = Birthday("Davy Crockett", MonthDay.of(8, 17), EarlierNotificationMode.WEEK)
        val firstWeekEarlierNotification = Notification(firstBirthday, date)
        val secondWeekEarlierNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(firstWeekEarlierNotification, secondWeekEarlierNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                emptyList(),
                listOf(firstWeekEarlierNotification, secondWeekEarlierNotification),
                emptyList()
            )
        )
    }

    @Test
    fun `should aggregate two month earlier notifications into one list`() {
        val date = MonthDay.of(3, 20)
        val firstBirthday = Birthday("George Takei", MonthDay.of(4, 20), EarlierNotificationMode.MONTH)
        val secondBirthday = Birthday("Andy Serkis", MonthDay.of(4, 20), EarlierNotificationMode.MONTH)
        val firstMonthEarlierNotification = Notification(firstBirthday, date)
        val secondMonthEarlierNotification = Notification(secondBirthday, date)

        val aggregation =
            notificationAggregator.aggregate(listOf(firstMonthEarlierNotification, secondMonthEarlierNotification), date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                emptyList(),
                emptyList(),
                listOf(firstMonthEarlierNotification, secondMonthEarlierNotification)
            )
        )
    }

    @Test
    fun `should aggregate several date notifications`() {
        val date = MonthDay.of(11, 21)
        val birthdays = listOf(
            Birthday("Voltaire", date),
            Birthday("Ed Harris", MonthDay.of(11, 28), EarlierNotificationMode.WEEK),
            Birthday("Friedrich Engels", MonthDay.of(11, 28), EarlierNotificationMode.WEEK),
            Birthday("William Blake", MonthDay.of(11, 28), EarlierNotificationMode.WEEK),
            Birthday("Samuel L. Jackson", MonthDay.of(12, 21), EarlierNotificationMode.MONTH),
            Birthday("Jane Fonda", MonthDay.of(12, 21), EarlierNotificationMode.MONTH),
        )
        val notifications = birthdays.map { Notification(it, date) }

        val aggregation =
            notificationAggregator.aggregate(notifications, date)

        Assertions.assertThat(aggregation).isEqualTo(
            AggregatedNotification(
                notifications.slice(0..0),
                notifications.slice(1..3),
                notifications.slice(4..5)
            )
        )
    }
}
