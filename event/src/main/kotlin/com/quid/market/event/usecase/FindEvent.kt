package com.quid.market.event.usecase

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindEvent {

    fun byId(eventId: Long): Event

    @Service
    @Transactional(readOnly = true)
    class FindEventImpl(
        val eventRepository: EventRepository
    ) : FindEvent {
        override fun byId(eventId: Long): Event = eventRepository.findById(eventId)
    }
}