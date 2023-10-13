package com.quid.market.coupon.gateway.repository.jpa

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.CouponType
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "coupon")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val couponName: String,
    val value: Int,
    val type: String,
    val expireDate: LocalDate,
    val regDate: LocalDate,
) {

    fun toCoupon() = Coupon(
        id = id,
        couponName = couponName,
        amount = CouponType.of(type, value),
        expireDate = expireDate,
        regDate = regDate,
    )
}

fun CouponEntity(coupon: Coupon) = CouponEntity(
    id = coupon.id,
    couponName = coupon.couponName,
    value = coupon.amount.value(),
    type = coupon.amount.type(),
    expireDate = coupon.expireDate,
    regDate = coupon.regDate,
)