package com.quid.market.coupon.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserCouponJpaRepository: JpaRepository<UserCouponEntity, Long> {
}