package com.quid.market.event.usecase

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindEvent {

    fun byId(eventId: Long): Event
    fun findAll(pageable: Pageable): Page<Event>

    @Service
    @Transactional(readOnly = true)
    class FindEventImpl(
        val eventRepository: EventRepository
    ) : FindEvent {
        override fun byId(eventId: Long): Event = eventRepository.findById(eventId)
        override fun findAll(pageable: Pageable): Page<Event> = eventRepository.findAll(pageable)
    }
}