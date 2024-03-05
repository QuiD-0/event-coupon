package com.quid.market.event.gateway.kafka.consumer

import com.quid.market.event.gateway.kafka.message.IssueCouponMessage
import com.quid.market.event.usecase.IssueEventCoupon
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class IssueEventCouponConsumer(
    private val issueEventCoupon: IssueEventCoupon
) {

    @KafkaListener(topics = ["issue-event-coupon"], groupId = "event-coupon")
    fun onMessage(message: IssueCouponMessage, ack: Acknowledgment) {
        issueEventCoupon.execute(message.userId, message.eventId)
        ack.acknowledge()
    }
}