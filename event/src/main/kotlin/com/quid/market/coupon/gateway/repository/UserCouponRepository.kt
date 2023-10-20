package com.quid.market.coupon.gateway.repository

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.jpa.UserCouponEntity
import com.quid.market.coupon.gateway.repository.jpa.UserCouponJpaRepository
import com.quid.market.coupon.gateway.repository.jpa.toCouponEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface UserCouponRepository {
    fun save(userCoupon: UserCoupon): UserCoupon
    fun findByUserId(userId: Long): List<UserCoupon>
    fun deleteAll()
    fun findById(userCouponId: Long): UserCoupon
    fun findByIdForUpdate(userCouponId: Long): UserCoupon

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

        override fun findById(userCouponId: Long): UserCoupon =
            userCouponJpaRepository.findByIdOrNull(userCouponId)
                ?.toUserCoupon()
                ?: throw NoSuchElementException("쿠폰이 존재하지 않습니다.")

        override fun findByIdForUpdate(userCouponId: Long): UserCoupon =
            userCouponJpaRepository.findByIdOrNullForUpdate(userCouponId)
                ?.toUserCoupon()
                ?: throw NoSuchElementException("쿠폰이 존재하지 않습니다.")
    }
}