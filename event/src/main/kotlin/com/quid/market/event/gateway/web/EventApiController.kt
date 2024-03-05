package com.quid.market.event.gateway.web

import com.quid.market.event.domain.Event
import com.quid.market.event.gateway.web.request.AssignCouponRequest
import com.quid.market.event.gateway.web.request.IssueCouponRequest
import com.quid.market.event.gateway.web.request.RegistEventRequest
import com.quid.market.event.usecase.AssignCoupon
import com.quid.market.event.usecase.FindEvent
import com.quid.market.event.usecase.IssueEventCoupon
import com.quid.market.event.usecase.RegistEvent
import com.quid.market.global.ApiResponse
import com.quid.market.global.Success
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventApiController(
    val registEvent: RegistEvent,
    val assignCoupon: AssignCoupon,
    val issueEventCoupon: IssueEventCoupon,
    val findEvent: FindEvent,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun registEvent(@RequestBody request: RegistEventRequest): ApiResponse<Event> =
        Success(registEvent.persist(request.toEvent()))

    @PostMapping("/coupon/assign")
    @ResponseStatus(OK)
    fun assignCoupon(@RequestBody request: AssignCouponRequest): ApiResponse<Event> =
        Success(assignCoupon.execute(request.eventId, request.eventCoupon))

    @PostMapping("/coupon/issue")
    @ResponseStatus(OK)
    fun issueCoupon(@RequestBody request: IssueCouponRequest): ApiResponse<Unit> =
        Success(issueEventCoupon.incoming(request.userId, request.eventId))

    @GetMapping("/list")
    @ResponseStatus(OK)
    fun getEventList(pageable: Pageable): ApiResponse<Page<Event>> =
        Success(findEvent.findAll(pageable))

    @GetMapping("/{eventId}")
    @ResponseStatus(OK)
    fun getEvent(@PathVariable eventId: Long): ApiResponse<Event> =
        Success(findEvent.byId(eventId))
}