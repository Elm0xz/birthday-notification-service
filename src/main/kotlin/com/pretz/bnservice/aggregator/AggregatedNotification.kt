package com.pretz.bnservice.aggregator

import com.pretz.bnservice.birthday.Notification

data class AggregatedNotification(
    private val onBirthdayNotifications: List<Notification>,
    private val weekEarlierNotifications: List<Notification>,
    private val monthEarlierNotifications: List<Notification>
)
