package com.quid.market.event.usecase

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface AssignCoupon {

    fun execute(couponId: Long, eventId: Long, count: Int): Event

    @Service
    @Transactional
    class AssignCouponImpl(
        val eventRepository: EventRepository,
    ) : AssignCoupon {
        override fun execute(couponId: Long, eventId: Long, count: Int): Event =
            eventRepository.findById(eventId)
                .assignCoupon(couponId, count)
                .let { eventRepository.save(it) }
                .also { eventRepository.initCount(eventId) }
    }
}