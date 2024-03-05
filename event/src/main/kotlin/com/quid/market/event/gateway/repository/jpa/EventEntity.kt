package com.quid.market.event.gateway.repository.jpa

import com.quid.market.event.domain.Event
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
    val couponId: Long? = null,
    val maxCount: Int? = null,
) {

    fun toEvent() = Event(
        id = id,
        eventName = eventName,
        description = description,
        eventStartDate = eventStartDate,
        eventEndDate = eventEndDate,
        regDate = regDate,
        couponId = couponId,
        maxCount = maxCount,
    )
}

fun toEventEntity(event: Event) = EventEntity(
    id = event.id,
    eventName = event.eventName,
    description = event.description,
    eventStartDate = event.eventStartDate,
    eventEndDate = event.eventEndDate,
    regDate = event.regDate,
    couponId = event.couponId,
    maxCount = event.maxCount,
)