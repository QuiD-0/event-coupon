package com.quid.market.event.gateway.repository

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.repository.jpa.EventEntity
import com.quid.market.event.gateway.repository.jpa.EventJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.lang.RuntimeException

interface EventRepository {

    fun save(event: Event): Event
    fun findById(eventId: Long): Event

    @Repository
    class EventRepositoryImpl(
        val eventJpaRepository: EventJpaRepository
    ) : EventRepository {
        override fun save(event: Event): Event =
            eventJpaRepository.save(EventEntity(event)).toEvent()

        override fun findById(eventId: Long): Event =
            eventJpaRepository.findByIdOrNull(eventId)
                ?.toEvent()
                ?: throw RuntimeException("Event not found")
    }
}