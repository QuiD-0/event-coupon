package com.quid.market.event.gateway.kafka.producer

import com.quid.market.event.gateway.kafka.message.IssueCouponMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class IssueEventCouponProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    fun send(eventId: Long, userId: Long) {
        kafkaTemplate.send("issue-event-coupon", IssueCouponMessage(eventId, userId))
    }
}