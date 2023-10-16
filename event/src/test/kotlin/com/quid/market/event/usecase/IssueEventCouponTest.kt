package com.quid.market.event.usecase

import com.quid.market.coupon.gateway.repository.UserCouponRepository
import com.quid.market.event.gateway.repository.EventRepository
import com.quid.market.fixture.EventFixture
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class IssueEventCouponTest{

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var userCouponRepository: UserCouponRepository

    @Test
    fun issueEventCouponTest(){
        val issueEventCoupon: IssueEventCoupon = IssueEventCoupon.IssueEventCouponUseCase(eventRepository, userCouponRepository)

        userCouponRepository.deleteAll()
        eventRepository.save(EventFixture().event)

        val threadPool = Executors.newFixedThreadPool(10)
        val latch = CountDownLatch(100)

        for(i in 1..100){
            threadPool.submit {
                try {
                    issueEventCoupon.execute(i.toLong(), 1)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        println("remain coupon count: ${eventRepository.findById(1).eventCoupon.count}")
    }
}