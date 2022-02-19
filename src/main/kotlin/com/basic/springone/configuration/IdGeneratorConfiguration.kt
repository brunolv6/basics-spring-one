package com.basic.springone.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.util.IdGenerator
import org.springframework.util.JdkIdGenerator
import java.util.*

@Configuration
class IdGeneratorConfiguration {

    @Bean
    @Profile("!default")
    fun fakeIdGenerator() =
        IdGenerator { UUID.fromString("05d1b645-a902-45f8-94b9-e148926656bd") }

    @Bean
    @Profile("default")
    fun idGenerator() =
        JdkIdGenerator()
}