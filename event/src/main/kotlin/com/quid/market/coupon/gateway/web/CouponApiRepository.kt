package com.quid.market.coupon.gateway.web

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.usecase.RegistCoupon
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/coupons")
class CouponApiRepository(
    val registCoupon: RegistCoupon,
) {

    @PostMapping
    fun registCoupon(@RequestBody request: RegistCouponRequest): Coupon =
        registCoupon.execute(request.coupon)

}