package com.quid.market.event.gateway.web

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.web.request.AssignCouponRequest
import com.quid.market.event.gateway.web.request.IssueCouponRequest
import com.quid.market.event.gateway.web.request.RegistEventRequest
import com.quid.market.event.usecase.AssignCoupon
import com.quid.market.event.usecase.IssueEventCoupon
import com.quid.market.event.usecase.RegistEvent
import com.quid.market.global.ApiResponse
import com.quid.market.global.Created
import com.quid.market.global.Success
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/events")
class EventApiController(
    val registEvent: RegistEvent,
    val assignCoupon: AssignCoupon,
    val issueEventCoupon: IssueEventCoupon,
) {

    @PostMapping
    fun registEvent(@RequestBody request: RegistEventRequest): ApiResponse<Event> =
        Created(data = registEvent.persist(request.toEvent()))

    @PostMapping("/coupon/assign")
    fun assignCoupon(@RequestBody request: AssignCouponRequest): ApiResponse<Event> =
        Success(data = assignCoupon.execute(request.eventId, request.eventCoupon))

    @PostMapping("/coupon/issue")
    fun issueCoupon(@RequestBody request: IssueCouponRequest): ApiResponse<UserCoupon> =
        Success(data = issueEventCoupon.execute(request.userId, request.eventId))

}