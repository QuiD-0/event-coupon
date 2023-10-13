package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.gateway.repository.jpa.CouponJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface CouponRepository {
    fun findById(couponId: Long): Coupon

    @Repository
    class CouponRepositoryImpl(
        val couponJpaRepository: CouponJpaRepository,
    ) : CouponRepository {
        override fun findById(couponId: Long): Coupon =
            couponJpaRepository.findByIdOrNull(couponId)
                ?.toCoupon()
                ?: throw RuntimeException("Coupon not found")
    }
}