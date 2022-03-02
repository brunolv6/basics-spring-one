package com.basic.springone.adapter.out.data

import com.basic.springone.adapter.`in`.web.request.UserRequest
import com.basic.springone.application.port.out.UserDataGateway
import com.basic.springone.commons.aws.SQSOperations
import org.springframework.stereotype.Component

@Component
class SQSUserDataGateway(
    private val sqsOperations: SQSOperations
) : UserDataGateway {

    override fun bulk(userRequest: UserRequest) {
        sqsOperations.convertAndSend(
            queueName = "spring-boot-poc",
            payload = userRequest,
            attributes = mapOf("UserEmail" to userRequest.email),
            delayInSeconds = 12
        )
    }

}