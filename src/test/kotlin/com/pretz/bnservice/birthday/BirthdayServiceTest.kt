package com.pretz.bnservice.birthday

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.MonthDay
import kotlin.test.Ignore

@ExtendWith(MockitoExtension::class)
class BirthdayServiceTest {

    private val birthdayRepository: BirthdayRepository = mock()
    private val notificationRepository: NotificationRepository = mock()
    private val birthdayService: BirthdayService = BirthdayService(birthdayRepository, notificationRepository)

    @Test
    fun `should add a birthday`() {
        val name = "George Boole"
        val date = MonthDay.of(11, 2)
        val birthday = Birthday(name, date, EarlierNotificationMode.NONE)
        setMockSaveRule(birthday)

        birthdayService.add(birthday)

        verify(birthdayRepository).save(Birthday("George Boole", MonthDay.of(11, 2), EarlierNotificationMode.NONE))
    }

    @Test
    fun `should register a notification task for particular birthday date`() {
        val name = "Hendrik Lorentz"
        val date = MonthDay.of(7, 18)
        val birthday = Birthday(name, date, EarlierNotificationMode.NONE)
        setMockSaveRule(birthday)

        birthdayService.add(birthday)

        verify(notificationRepository).save(
            Notification(
                Birthday("Hendrik Lorentz", MonthDay.of(7, 18), EarlierNotificationMode.NONE),
                MonthDay.of(7, 18)
            )
        )
    }

    @Test
    fun `should register first notification task for particular birthday date and second for a week before`() {
        val name = "Johannes Kepler"
        val date = MonthDay.of(12, 27)
        val birthday = Birthday(name, date, EarlierNotificationMode.WEEK)
        setMockSaveRule(birthday)

        birthdayService.add(birthday)

        val expectedBirthday = Birthday("Johannes Kepler", MonthDay.of(12, 27), EarlierNotificationMode.WEEK)
        verify(notificationRepository).saveAll(
            setOf(
                Notification(expectedBirthday, MonthDay.of(12, 27)),
                Notification(expectedBirthday, MonthDay.of(12, 20))
            )
        )
    }

    @Test
    fun `should register first notification task for particular birthday date and second for a month before`() {
        val name = "Max Planck"
        val date = MonthDay.of(4, 23)
        val birthday = Birthday(name, date, EarlierNotificationMode.MONTH)
        setMockSaveRule(birthday)

        birthdayService.add(birthday)

        val expectedBirthday = Birthday("Max Planck", MonthDay.of(4, 23), EarlierNotificationMode.MONTH)
        verify(notificationRepository).saveAll(
            setOf(
                Notification(expectedBirthday, MonthDay.of(4, 23)),
                Notification(expectedBirthday, MonthDay.of(3, 23))
            )
        )
    }

    @Test
    fun `should register first notification task for particular birthday date, second for a week before and third for a month before`() {
        val name = "Albert Einstein"
        val date = MonthDay.of(3, 14)
        val birthday = Birthday(name, date, EarlierNotificationMode.WEEK_MONTH)
        setMockSaveRule(birthday)

        birthdayService.add(birthday)

        val expectedBirthday = Birthday("Albert Einstein", MonthDay.of(3, 14), EarlierNotificationMode.WEEK_MONTH)
        verify(notificationRepository).saveAll(
            setOf(
                Notification(expectedBirthday, MonthDay.of(3, 14)),
                Notification(expectedBirthday, MonthDay.of(3, 7)),
                Notification(expectedBirthday, MonthDay.of(2, 14))
            )
        )
    }

    @Test
    fun `should delete a birthday`() {
        val name = "Alan Turing"
        val date = MonthDay.of(6, 23)
        val birthday = Birthday(name, date, EarlierNotificationMode.NONE)

        birthdayService.delete(birthday)

        verify(birthdayRepository).delete(Birthday("Alan Turing", MonthDay.of(6, 23), EarlierNotificationMode.NONE))
    }

    //TODO
    @Ignore("Consider using in-memory db for this test in order to minimize mocking")
    @Test
    fun `should delete all notifications when deleting a birthday`() {
        val name = "Vladimir Lenin"
        val date = MonthDay.of(4, 22)
        val birthday = Birthday(name, date, EarlierNotificationMode.WEEK_MONTH)

        birthdayService.delete(birthday)

        val expectedDeletedBirthday = Birthday("Vladimir Lenin", MonthDay.of(4, 22), EarlierNotificationMode.WEEK_MONTH)
        verify(notificationRepository).deleteAll(
            setOf(
                Notification(expectedDeletedBirthday, MonthDay.of(4, 22)),
                Notification(expectedDeletedBirthday, MonthDay.of(4, 15)),
                Notification(expectedDeletedBirthday, MonthDay.of(3, 22))
            )
        )
    }

    private fun setMockSaveRule(birthday: Birthday) {
        whenever(birthdayRepository.save(birthday)).thenReturn(birthday)
    }
}
