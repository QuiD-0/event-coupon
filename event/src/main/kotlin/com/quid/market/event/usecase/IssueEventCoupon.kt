package com.quid.market.event.usecase

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.CouponRepository
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
        val couponRepository: CouponRepository,
        val userCouponRepository: UserCouponRepository,
        val issueEventCouponProducer: IssueEventCouponProducer
    ) : IssueEventCoupon {

        override fun incoming(eventId: Long, userId: Long) {
            val currentCount = eventRepository.increaseCount(eventId)
            val maxCount = eventRepository.findById(eventId).maxCount!!

            require(currentCount <= maxCount) { "쿠폰이 모두 발급 되었습니다." }

            issueEventCouponProducer.send(eventId, userId)
        }


        @Transactional
        override fun execute(userId: Long, eventId: Long): UserCoupon =
            eventRepository.findById(eventId)
                .let { couponRepository.findById(it.couponId!!) }
                .let { UserCoupon(userId = userId, coupon = it) }
                .let { userCouponRepository.save(it) }
    }
}
