package com.quid.market.event.gateway.web

import com.quid.market.event.gateway.web.request.IssueCouponRequest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import java.lang.Thread.sleep
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@Disabled
class EventApiControllerTest{

    @Test
    fun test(){
        val client = client()

        val threadPool = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(100)

        for(i in 1..100){
            threadPool.submit {
                try {
                    client.post()
                        .bodyValue(IssueCouponRequest(i.toLong(), 1))
                        .retrieve()
                        .bodyToMono(String::class.java)
                        .log()
                        .subscribe()
                } catch (e: Exception) {
                    e.printStackTrace()
                }finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        sleep(10000)
    }

    private fun client() = WebClient.builder()
        .baseUrl("http://localhost:8080/api/events/coupon/issue")
        .build()
}

