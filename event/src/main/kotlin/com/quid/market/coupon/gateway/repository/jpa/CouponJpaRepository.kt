package com.quid.market.coupon.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CouponJpaRepository: JpaRepository<CouponEntity, Long> {
}