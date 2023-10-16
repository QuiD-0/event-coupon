package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.gateway.repository.jpa.CouponJpaRepository
import com.quid.market.coupon.gateway.repository.jpa.toCouponEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface CouponRepository {
    fun findById(couponId: Long): Coupon
    fun save(coupon: Coupon): Coupon

    @Repository
    class CouponRepositoryImpl(
        val couponJpaRepository: CouponJpaRepository,
    ) : CouponRepository {
        override fun findById(couponId: Long): Coupon =
            couponJpaRepository.findByIdOrNull(couponId)
                ?.toCoupon()
                ?: throw NoSuchElementException("Coupon not found")

        override fun save(coupon: Coupon): Coupon =
            couponJpaRepository.save(toCouponEntity(coupon))
                .toCoupon()
    }
}