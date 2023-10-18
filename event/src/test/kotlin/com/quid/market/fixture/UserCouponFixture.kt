package com.quid.market.fixture

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.FixedAmountCoupon
import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import java.time.LocalDate

class UserCouponFixture {

    fun repository() = FakeUserCouponRepository()

    fun userCoupon() =  UserCoupon(
        id= 1,
        userId = 1,
        coupon = Coupon(
            id = 1L,
            couponName = "쿠폰",
            value = FixedAmountCoupon(1000),
            expireDate = LocalDate.now().plusDays(7),
        ),
        usedDate = null,
    )
}

class FakeUserCouponRepository: UserCouponRepository {
    private val userCoupons = mutableListOf<UserCoupon>()

    override fun save(userCoupon: UserCoupon): UserCoupon {
        userCoupons.add(userCoupon)
        return userCoupon
    }

    override fun findByUserId(userId: Long): List<UserCoupon> {
        return userCoupons.filter { it.userId == userId }
    }

    override fun deleteAll() {
        userCoupons.clear()
    }

    override fun findById(userCouponId: Long): UserCoupon {
        return userCoupons.first { it.id == userCouponId }
    }

}