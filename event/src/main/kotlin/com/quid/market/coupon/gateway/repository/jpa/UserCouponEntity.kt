package com.quid.market.coupon.gateway.repository.jpa

import com.quid.market.coupon.domain.UserCoupon
import java.time.LocalDate
import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "user_coupon")
class UserCouponEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val userId: Long,
    @ManyToOne(fetch = LAZY)
    val coupon: CouponEntity,
    val usedDate: LocalDate? = null,
    val regDate: LocalDate,
) {
    fun toUserCoupon() = UserCoupon(
        id = id,
        userId = userId,
        coupon = coupon.toCoupon(),
        usedDate = usedDate,
        regDate = regDate,
    )
}

fun UserCouponEntity(userCoupon: UserCoupon) = UserCouponEntity(
    id = userCoupon.id,
    userId = userCoupon.userId,
    coupon = toCouponEntity(userCoupon.coupon),
    usedDate = userCoupon.usedDate,
    regDate = userCoupon.regDate,
)