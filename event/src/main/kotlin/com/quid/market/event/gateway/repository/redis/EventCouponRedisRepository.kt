package com.quid.market.event.gateway.repository.redis

import com.quid.market.event.domain.EventCoupon
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class EventCouponRedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun save(eventCoupon: EventCoupon) {
        redisTemplate.opsForHash<Long, Int>().put(KEY, eventCoupon.eventId, eventCoupon.count)
    }

    fun findByIdOrNull(eventId: Long): EventCoupon? =
        redisTemplate.opsForHash<Long, Int>().get(KEY, eventId)?.let { EventCoupon(eventId, it) }

    fun incr(eventId: Long) {
        redisTemplate.opsForHash<Long, Int>().increment(KEY, eventId, 1)
    }

    companion object {
        private const val KEY = "eventCoupon"
    }
}