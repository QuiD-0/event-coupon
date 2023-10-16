package com.quid.market.coupon.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserCouponJpaRepository: JpaRepository<UserCouponEntity, Long> {
    fun findByUserId(userId: Long): List<UserCouponEntity>
    fun existsByUserIdAndCoupon(userId: Long, coupon: CouponEntity): Boolean
}