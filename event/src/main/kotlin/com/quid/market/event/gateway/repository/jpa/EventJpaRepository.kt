package com.quid.market.event.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EventJpaRepository: JpaRepository<EventEntity, Long> {

    @Query("delete from EventEntity")
    override fun deleteAll()
}