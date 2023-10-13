package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.jpa.UserCouponEntity
import com.quid.market.coupon.gateway.repository.jpa.UserCouponJpaRepository
import org.springframework.stereotype.Repository

interface UserCouponRepository {
    fun save(userCoupon: UserCoupon): UserCoupon
    fun findByUserId(userId: Long): List<UserCoupon>

    @Repository
    class UserCouponRepositoryImpl(
        val userCouponJpaRepository: UserCouponJpaRepository,
    ): UserCouponRepository {
        override fun save(userCoupon: UserCoupon): UserCoupon =
            userCouponJpaRepository.save(UserCouponEntity(userCoupon)).toUserCoupon()

        override fun findByUserId(userId: Long) =
            userCouponJpaRepository.findByUserId(userId)
                .map { it.toUserCoupon() }
    }
}