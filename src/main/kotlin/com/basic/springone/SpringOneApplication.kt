package com.basic.springone

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableAutoConfiguration
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
class SpringOneApplication

fun main(args: Array<String>) {
	runApplication<SpringOneApplication>(*args)
}
