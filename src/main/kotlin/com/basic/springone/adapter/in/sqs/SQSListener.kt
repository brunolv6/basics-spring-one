package com.basic.springone.adapter.`in`.sqs

import com.basic.springone.adapter.`in`.web.request.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy.ON_SUCCESS
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class SQSListener(
    private val objectMapper: ObjectMapper
) {

    @SqsListener(
        value = ["spring-boot-poc"],
        deletionPolicy = ON_SUCCESS
    )
    fun onMessageReceived(
        payload: String,
        @Header("UserEmail") userEmail: String
    ) =
        println("UserEmail on header: $userEmail, and payload: " + userRequest(payload))

    private fun userRequest(payload: String) =
        objectMapper.readValue<UserRequest>(payload)
}