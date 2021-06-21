package com.pretz.bnservice.birthday

import org.springframework.data.repository.CrudRepository
import java.time.MonthDay

interface BirthdayRepository : CrudRepository<Birthday, Long> {

    fun findAllByDate(date: MonthDay): List<Birthday>
}
