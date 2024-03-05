package com.quid.market.fixture

import com.quid.market.event.domain.Event
import com.quid.market.event.domain.EventCoupon
import java.time.LocalDateTime

class EventFixture {

    val event: Event
        get() = Event(
            id = 1L,
            eventName = "이벤트",
            description = "이벤트 설명",
            eventStartDate = LocalDateTime.now().minusDays(7),
            eventEndDate = LocalDateTime.now().plusDays(7),
            eventCoupon = EventCoupon(
                count = 100,
                couponId = 1L
            ),
        )
}