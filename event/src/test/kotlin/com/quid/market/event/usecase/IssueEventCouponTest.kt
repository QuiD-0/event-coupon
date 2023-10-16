package com.quid.market.event.usecase

import com.quid.market.coupon.gateway.repository.UserCouponRepository
import com.quid.market.event.gateway.repository.EventRepository
import com.quid.market.fixture.EventFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IssueEventCouponTest{

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var userCouponRepository: UserCouponRepository


    @Test
    fun issueEventCouponTest(){
        val issueEventCoupon = IssueEventCoupon.IssueEventCouponUseCase(eventRepository, userCouponRepository)


        issueEventCoupon.execute(1, 1)
    }


    @BeforeEach
    fun setUp(){
        eventRepository.deleteAll()
        eventRepository.save(EventFixture().event)
    }
}