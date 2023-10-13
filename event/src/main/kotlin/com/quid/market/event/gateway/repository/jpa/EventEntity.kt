package com.quid.market.event.gateway.repository.jpa

import com.quid.market.coupon.gateway.repository.jpa.CouponEntity
import com.quid.market.event.domain.Event
import com.quid.market.event.domain.RemainCoupon
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
    val couponCount: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    val coupon: CouponEntity? = null,
) {

    fun toEvent() = Event(
        id = id,
        eventName = eventName,
        description = description,
        eventStartDate = eventStartDate,
        eventEndDate = eventEndDate,
        regDate = regDate,
        coupon = RemainCoupon(couponCount, coupon?.toCoupon()),
    )
}

fun EventEntity(event: Event) = EventEntity(
    id = event.id,
    eventName = event.eventName,
    description = event.description,
    eventStartDate = event.eventStartDate,
    eventEndDate = event.eventEndDate,
    regDate = event.regDate,
    couponCount = event.coupon.count,
    coupon = event.coupon.coupon?.let { CouponEntity(it) },
)