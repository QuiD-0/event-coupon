package com.quid.market.coupon.gateway.web

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.web.request.RegistCouponRequest
import com.quid.market.coupon.usecase.FindUserCoupon
import com.quid.market.coupon.usecase.RegistCoupon
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/coupons")
class CouponApiRepository(
    val registCoupon: RegistCoupon,
    val findUserCoupon: FindUserCoupon,
) {

    @PostMapping
    fun registCoupon(@RequestBody request: RegistCouponRequest): Coupon =
        registCoupon.execute(request.coupon)

    @GetMapping("/user/{userId}")
    fun getCouponByUserId(@PathVariable userId: Long): List<UserCoupon> =
        findUserCoupon.byUserId(userId)

}