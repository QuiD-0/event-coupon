package com.quid.market.event.usecase

import com.quid.market.coupon.domain.Coupon
import com.quid.market.event.domain.Event
import com.quid.market.event.domain.EventCoupon
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AssignCoupon {

    fun execute(eventId: Long, eventCoupon: EventCoupon): Event

    @Service
    @Transactional
    class AssignCouponImpl(
        val eventRepository: EventRepository,
    ) : AssignCoupon {
        override fun execute(eventId: Long, eventCoupon: EventCoupon): Event =
            eventRepository.findById(eventId)
                .assignCoupon(eventCoupon)
                .let { eventRepository.save(it) }
    }
}