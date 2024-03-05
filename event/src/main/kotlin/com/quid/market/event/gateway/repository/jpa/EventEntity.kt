package com.quid.market.event.gateway.repository.jpa

import com.quid.market.event.domain.Event
import com.quid.market.event.domain.EventCoupon
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "event")
class EventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
    val regDate: LocalDateTime,
    val couponCount: Int? = null,
    val couponId: Long? = null,
) {

    fun toEvent() = Event(
        id = id,
        eventName = eventName,
        description = description,
        eventStartDate = eventStartDate,
        eventEndDate = eventEndDate,
        regDate = regDate,
        eventCoupon = couponId?.let { EventCoupon(couponId = it, count = couponCount!!) },
    )
}

fun toEventEntity(event: Event) = EventEntity(
    id = event.id,
    eventName = event.eventName,
    description = event.description,
    eventStartDate = event.eventStartDate,
    eventEndDate = event.eventEndDate,
    regDate = event.regDate,
    couponCount = event.eventCoupon?.count,
    couponId = event.eventCoupon?.couponId,
)