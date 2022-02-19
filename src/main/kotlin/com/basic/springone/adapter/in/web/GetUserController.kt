package com.basic.springone.adapter.`in`.web

import com.basic.springone.adapter.`in`.request.UserRequest
import com.basic.springone.adapter.out.response.UserResponse
import com.basic.springone.ports.out.OpenApi
import com.basic.springone.ports.out.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class GetUserController(
    private val openApi: OpenApi
) {

    @GetMapping("/v1/user")
    fun getUser(@RequestBody @Valid userRequest: UserRequest): UserResponse =
        userRequest.mapperToUserResponse(fetchEntries())


    private fun fetchEntries(): ApiResponse? =
        openApi.getEntries()
}