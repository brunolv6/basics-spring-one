package com.basic.springone.adapter.`in`.request

import com.basic.springone.adapter.out.response.UserResponse
import com.basic.springone.adapter.validators.IsValidPassword
import com.basic.springone.ports.out.response.ApiResponse
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserRequest(
    @field:[NotBlank Email] val email: String,
    @field:[IsValidPassword] val password: String
) {
    fun mapperToUserResponse(apiResponse: ApiResponse?): UserResponse =
        UserResponse(
            emailWithPassword = this.toString(),
            entriesSize = apiResponse?.count ?: 0,
            entries = apiResponse?.entries?.map { it.mapperToEntryResponse() }
        )
}
