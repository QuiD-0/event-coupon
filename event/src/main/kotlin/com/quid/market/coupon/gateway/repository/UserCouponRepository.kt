package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.jpa.UserCouponEntity
import com.quid.market.coupon.gateway.repository.jpa.UserCouponJpaRepository
import org.springframework.stereotype.Repository

interface UserCouponRepository {
    fun save(userCoupon: UserCoupon): UserCoupon

    @Repository
    class UserCouponRepositoryImpl(
        val userCouponJpaRepository: UserCouponJpaRepository,
    ): UserCouponRepository {
        override fun save(userCoupon: UserCoupon): UserCoupon =
            userCouponJpaRepository.save(UserCouponEntity(userCoupon)).toUserCoupon()
    }
}