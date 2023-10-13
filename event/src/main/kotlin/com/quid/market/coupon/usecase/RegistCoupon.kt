package com.quid.market.coupon.usecase

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.gateway.repository.CouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegistCoupon {
    fun execute(coupon: Coupon): Coupon

    @Service
    @Transactional
    class RegistCouponImpl(
        val couponRepository: CouponRepository,
    ) : RegistCoupon {
        override fun execute(coupon: Coupon): Coupon =
            couponRepository.save(coupon)
    }
}