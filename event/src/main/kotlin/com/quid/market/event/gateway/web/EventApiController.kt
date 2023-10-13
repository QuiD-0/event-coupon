package com.quid.market.event.gateway.web

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.web.request.AssignCouponRequest
import com.quid.market.event.gateway.web.request.IssueCouponRequest
import com.quid.market.event.gateway.web.request.RegistEventRequest
import com.quid.market.event.usecase.AssignCoupon
import com.quid.market.event.usecase.IssueEventCoupon
import com.quid.market.event.usecase.RegistEvent
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/events")
class EventApiController(
    val registEvent: RegistEvent,
    val assignCoupon: AssignCoupon,
    val issueEventCoupon: IssueEventCoupon,
) {

    @PostMapping
    fun registEvent(@RequestBody request: RegistEventRequest): Event =
        registEvent.persist(request.toEvent())

    @PostMapping("/coupon/assign")
    fun assignCoupon(@RequestBody request: AssignCouponRequest): Event =
        assignCoupon.execute(request.eventId, request.coupon, request.count)

    @PostMapping("/coupon/issue")
    fun issueCoupon(@RequestBody request: IssueCouponRequest): UserCoupon =
        issueEventCoupon.execute(request.userId, request.eventId)

}