/*
package com.pretz.bnservice.scheduler

import org.junit.jupiter.api.Test
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

//TODO !! I think that maybe the better approach would be to test service and aggregating logic separately as unit tests
@SpringJUnitConfig(TestBirthdaySchedulerConfig::class)
class BirthdaySchedulerTestOld {

*/
/*    @Autowired
    private lateinit var scheduler: BirthdayScheduler

    @Autowired
    private lateinit var repository: BirthdayRepository

    @Autowired
    private lateinit var clock: MockClock

    @Autowired
    private lateinit var scheduledTaskHolder: ScheduledTaskHolder*//*


    //TODO Scheduler should accept a name, a day-month date (formatted in some generally accepted way, f.e. dd-MM)
    //TODO and then schedule a task of sending a notification to an user
    //TODO Caveats: Every birthday can have a custom set of notification dates
    //TODO On particular date, all of the notification planned for this day should be aggregated in groups
    //TODO("upcoming birthdays" by date and today birthdays) and then sent in one mail.

    //inputs: name, date
    //state: database of planned notifications
    //outputs: aggregated list of notifications on particular day

    //mocks: database (can use in-memory cache as a mock), email service

    @Test
    fun `should register a birthday notification task for particular name date`() {
*/
/*
        val name = "Test Name"
        val date = MonthDay.of(12, 3)
        val birthday = Birthday(name, date, EarlierNotification.NONE)
        repository.save(birthday)

        //when scheduling a notification sending task
        clock.useFixedClock(LocalDateTime.of(Year.now().atMonthDay(date), LocalTime.of(0,0)))

        //then it should send a notification on a set date
        assertEquals(1,1)*//*

    }

    //TODO test for every month?

    @Test
    fun `should register one birthday notification task for particular date and another for a week before`() {
        //given name and date and additional parameter for scheduling an additional notification a week earlier

        //when scheduling a notification sending task

        //then it should send a notification on a set date and another for a week before
    }

    @Test
    fun `should register one birthday notification task for particular date and another for a month before`() {
        //given name and date and additional parameter for scheduling an additional notification a month earlier

        //when scheduling a notification sending task

        //then it should send a notification on a set date and another for a month before
    }

    @Test
    fun `should register one birthday notification task for particular date, another for a month before and yet another for a week before`() {
        //given name and date and additional parameter for scheduling an additional notification a month and a week earlier

        //when scheduling a notification sending task

        //then it should send a notification on a set date
    }

    @Test
    fun `should aggregate two scheduled tasks into one notification when on the same date`() {
        //given two schedulings on the same date, but different names

        //when scheduling a notification sending task

        //then it should send one notification on a set date, but with both names
    }

    @Test
    fun `should aggregate two scheduled tasks into one notification when birthday and another earlier notification on the same date`() {
        //given two schedulings, first for some particular date, second for another date, but with additional earlier notification having the same date as first one

        //when scheduling a notification sending task

        //then it should send one notification on a set date, but with both names; then another separate notification on the second date
    }
}
*/
