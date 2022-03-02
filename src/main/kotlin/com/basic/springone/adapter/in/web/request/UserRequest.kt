package com.basic.springone.adapter.`in`.web.request

import com.basic.springone.adapter.`in`.web.validators.IsValidPassword
import com.basic.springone.adapter.`in`.web.response.UserResponse
import com.basic.springone.ports.out.response.ApiResponse
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserRequest(
    @field:[NotBlank Email] val email: String,
    @field:[IsValidPassword] val password: String
) {
    fun mapperToUserResponse(uuid: UUID, apiResponse: ApiResponse?): UserResponse =
        UserResponse(
            uuid = uuid,
            emailWithPassword = this.toString(),
            entriesSize = apiResponse?.count ?: 0,
            entries = apiResponse?.entries?.map { it.mapperToEntryResponse() }
        )
}
