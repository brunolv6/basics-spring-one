package com.basic.springone.adapter.`in`.response

import java.util.*

data class UserResponse(
    val uuid: UUID? = UUID.randomUUID(),
    val emailWithPassword: String
)
