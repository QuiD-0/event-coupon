package com.quid.market.event.gateway.repository

import com.quid.market.event.domain.Event
import com.quid.market.event.domain.EventCoupon
import com.quid.market.event.gateway.repository.jpa.EventJpaRepository
import com.quid.market.event.gateway.repository.jpa.toEventEntity
import com.quid.market.event.gateway.repository.redis.EventCouponRedisRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface EventRepository {

    fun save(event: Event): Event
    fun findById(eventId: Long): Event
    fun findAll(pageable: Pageable): Page<Event>
    fun deleteAll()
    fun initCount(eventId: Long)
    fun increaseCount(eventId: Long): Long

    @Repository
    class EventRepositoryImpl(
        val eventJpaRepository: EventJpaRepository,
        val eventCouponRedisRepository: EventCouponRedisRepository
    ) : EventRepository {
        override fun save(event: Event): Event =
            eventJpaRepository.save(toEventEntity(event))
                .toEvent()

        override fun findById(eventId: Long): Event =
            eventJpaRepository.findByIdOrNull(eventId)
                ?.toEvent()
                ?: throw NoSuchElementException("Event not found")

        override fun findAll(pageable: Pageable): Page<Event> =
            eventJpaRepository.findAll(pageable)
                .map { it.toEvent() }

        override fun deleteAll() {
            eventJpaRepository.deleteAll()
        }

        override fun initCount(eventId: Long) {
            eventCouponRedisRepository.save(EventCoupon(eventId, 0))
        }

        override fun increaseCount(eventId: Long): Long {
            return eventCouponRedisRepository.incr(eventId)
        }
    }
}
