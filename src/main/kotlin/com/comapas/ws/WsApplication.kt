package com.comapas.ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class WsApplication {

    @Primary
    @Bean
    fun getCustomRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    runApplication<WsApplication>(*args)
}
