package com.quid.market.event.gateway.repository

import com.quid.market.event.domain.Event
import org.springframework.stereotype.Repository

interface EventRepository {

    fun save(event: Event): Event

    @Repository
    class EventRepositoryImpl : EventRepository {
        override fun save(event: Event): Event {
            return event
        }
    }
}