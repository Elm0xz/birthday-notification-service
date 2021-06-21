package com.pretz.bnservice.birthday

import java.time.MonthDay

data class Birthday(
    val name: String,
    val date: MonthDay,
    val notificationMode: EarlierNotificationMode = EarlierNotificationMode.NONE
)
