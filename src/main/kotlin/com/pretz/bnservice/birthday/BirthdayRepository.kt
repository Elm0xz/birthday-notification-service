package com.pretz.bnservice.birthday

import org.springframework.data.repository.CrudRepository

interface BirthdayRepository : CrudRepository<Birthday, Long> {
}
