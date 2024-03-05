package com.quid.market.event.usecase

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import com.quid.market.event.gateway.kafka.producer.IssueEventCouponProducer
import com.quid.market.event.gateway.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface IssueEventCoupon {

    fun incoming(eventId: Long, userId: Long)

    fun execute(userId: Long, eventId: Long): UserCoupon

    @Service
    class IssueEventCouponUseCase(
        val eventRepository: EventRepository,
        val userCouponRepository: UserCouponRepository,
        val issueEventCouponProducer: IssueEventCouponProducer
    ) : IssueEventCoupon {

        override fun incoming(eventId: Long, userId: Long) {
            issueEventCouponProducer.send(eventId, userId)
        }

        @Transactional
        override fun execute(userId: Long, eventId: Long): UserCoupon =
            eventRepository.findByIdForUpdate(eventId)
                .issueCoupon()
                .also { eventRepository.save(it) }
                .let { UserCoupon(userId = userId, coupon = it.eventCoupon?.coupon!!) }
                .let { userCouponRepository.save(it) }
    }
}