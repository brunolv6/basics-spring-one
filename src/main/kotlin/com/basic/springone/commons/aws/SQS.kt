package com.basic.springone.commons.aws

import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.model.MessageAttributeValue
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentHashMap

interface SQSOperations {
    fun convertAndSend(
        queueName: String,
        payload: Any,
        attributes: Map<String, String>? = null,
        delayInSeconds: Int? = null
    )
}

class DefaultSQSOperations(
    private val amazonSQS: AmazonSQSAsync,
    private val objectMapper: ObjectMapper
) : SQSOperations {
    private val queueUrlCache = ConcurrentHashMap<String, String>()

    override fun convertAndSend(
        queueName: String,
        payload: Any,
        attributes: Map<String, String>?,
        delayInSeconds: Int?
    ) {

        val sendMessageRequest = SendMessageRequest().apply {
            messageBody = objectMapper.writeValueAsString(payload)
            // search for the queueName's url
            queueUrl = queueUrlCache.getOrPut(queueName) {
                amazonSQS.getQueueUrl(queueName).queueUrl
            }
            // message attributes return on Header
            messageAttributes = attributes?.mapValues {
                MessageAttributeValue()
                    .withStringValue(it.value)
                    .withDataType("String")
            } ?: emptyMap()
            if (delayInSeconds != null && delayInSeconds > 0) {
                delaySeconds = delayInSeconds
            }
        }

        val sendMessageResult = amazonSQS.sendMessage(sendMessageRequest)
        println("SQS message id = ${sendMessageResult.messageId} send: $sendMessageResult")
    }
}