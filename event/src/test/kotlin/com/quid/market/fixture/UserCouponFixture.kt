package com.quid.market.fixture

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository

class UserCouponFixture {

    fun repository() = FakeUserCouponRepository()

    fun userCoupon() =  UserCoupon(
        id= 1,
        userId = 1,
        coupon = CouponFixture().fixedAmountCoupon(),
        usedDate = null,
    )
}

class FakeUserCouponRepository: UserCouponRepository {
    private val userCoupons = mutableMapOf<Long, UserCoupon>()

    override fun save(userCoupon: UserCoupon): UserCoupon {
        userCoupons[userCoupon.id!!] = userCoupon
        return userCoupon
    }

    override fun findByUserId(userId: Long): List<UserCoupon> {
        return userCoupons.values.filter { it.userId == userId }
    }

    override fun deleteAll() {
        userCoupons.clear()
    }

    override fun findById(userCouponId: Long): UserCoupon {
        return userCoupons[userCouponId]!!
    }

}