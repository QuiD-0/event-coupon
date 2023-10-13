package com.quid.market.coupon.usecase

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindUserCoupon {
    fun byUserId(userId: Long): List<UserCoupon>

    @Service
    @Transactional(readOnly = true)
    class FindUserCouponImpl(
        val userCouponRepository: UserCouponRepository,
    ) : FindUserCoupon {
        override fun byUserId(userId: Long): List<UserCoupon> =
            userCouponRepository.findByUserId(userId)
    }
}