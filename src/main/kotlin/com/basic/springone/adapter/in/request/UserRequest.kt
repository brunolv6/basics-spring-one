package com.basic.springone.adapter.`in`.request

import com.basic.springone.adapter.validators.IsValidPassword
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserRequest(
    @field:[NotBlank Email] val email: String,
    @field:[IsValidPassword] val password: String
)
