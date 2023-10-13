package com.quid.market.coupon.gateway.web

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.web.request.RegistCouponRequest
import com.quid.market.coupon.usecase.FindUserCoupon
import com.quid.market.coupon.usecase.RegistCoupon
import com.quid.market.global.ApiResponse
import com.quid.market.global.Created
import com.quid.market.global.Success
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/coupons")
class CouponApiRepository(
    val registCoupon: RegistCoupon,
    val findUserCoupon: FindUserCoupon,
) {

    @PostMapping
    fun registCoupon(@RequestBody request: RegistCouponRequest): ApiResponse<Coupon> =
        Created(data = registCoupon.execute(request.coupon))

    @GetMapping("/user/{userId}")
    fun getCouponByUserId(@PathVariable userId: Long): ApiResponse<List<UserCoupon>> =
        Success(data = findUserCoupon.byUserId(userId))

}