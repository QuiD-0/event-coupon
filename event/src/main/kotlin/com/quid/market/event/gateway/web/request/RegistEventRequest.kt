package com.quid.market.event.gateway.web.request

import com.quid.market.event.domain.Event
import java.time.LocalDateTime

data class RegistEventRequest(
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
) {
    fun toEvent(): Event = Event(
        eventName = eventName,
        description = description,
        eventStartDate = eventStartDate,
        eventEndDate = eventEndDate
    )
}