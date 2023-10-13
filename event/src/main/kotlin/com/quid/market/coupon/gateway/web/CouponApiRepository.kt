package com.quid.market.coupon.gateway.web

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.web.request.RegistCouponRequest
import com.quid.market.coupon.usecase.FindUserCoupon
import com.quid.market.coupon.usecase.RegistCoupon
import com.quid.market.global.ApiResponse
import com.quid.market.global.Success
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/coupons")
class CouponApiRepository(
    val registCoupon: RegistCoupon,
    val findUserCoupon: FindUserCoupon,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun registCoupon(@RequestBody request: RegistCouponRequest): ApiResponse<Coupon> =
        Success(registCoupon.execute(request.coupon))

    @GetMapping("/user/{userId}")
    @ResponseStatus(OK)
    fun getCouponByUserId(@PathVariable userId: Long): ApiResponse<List<UserCoupon>> =
        Success(findUserCoupon.byUserId(userId))

}