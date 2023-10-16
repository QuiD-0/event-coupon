package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.jpa.UserCouponEntity
import com.quid.market.coupon.gateway.repository.jpa.UserCouponJpaRepository
import com.quid.market.coupon.gateway.repository.jpa.toCouponEntity
import org.springframework.stereotype.Repository

interface UserCouponRepository {
    fun save(userCoupon: UserCoupon): UserCoupon
    fun findByUserId(userId: Long): List<UserCoupon>
    fun deleteAll()

    @Repository
    class UserCouponRepositoryImpl(
        val userCouponJpaRepository: UserCouponJpaRepository,
    ): UserCouponRepository {
        override fun save(userCoupon: UserCoupon): UserCoupon =
            takeUnless { userCouponJpaRepository.existsByUserIdAndCoupon(userCoupon.userId, toCouponEntity(userCoupon.coupon)) }
                ?. let { userCouponJpaRepository.save(UserCouponEntity(userCoupon)).toUserCoupon() }
                ?: throw RuntimeException("이미 발급된 쿠폰입니다.")

        override fun findByUserId(userId: Long) =
            userCouponJpaRepository.findByUserId(userId)
                .map { it.toUserCoupon() }

        override fun deleteAll() {
            userCouponJpaRepository.deleteAll()
        }
    }
}