package com.quid.market.event.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface EventJpaRepository: JpaRepository<EventEntity, Long> {
}