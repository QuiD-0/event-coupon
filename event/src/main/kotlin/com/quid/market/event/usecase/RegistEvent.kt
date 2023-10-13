package com.quid.market.event.usecase

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegistEvent {

    fun persist(event: Event): Event

    @Service
    @Transactional
    class RegistEventImpl(
        val eventRepository: EventRepository
    ) : RegistEvent {
        override fun persist(event: Event): Event =
            eventRepository.save(event)
    }

}