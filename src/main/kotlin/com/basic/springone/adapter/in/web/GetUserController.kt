package com.basic.springone.adapter.`in`.web

import com.basic.springone.adapter.`in`.web.request.UserRequest
import com.basic.springone.adapter.`in`.web.response.UserResponse
import com.basic.springone.ports.out.OpenApi
import com.basic.springone.ports.out.response.ApiResponse
import org.springframework.util.IdGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class GetUserController(
    private val openApi: OpenApi,
    private val idGenerator: IdGenerator
) {

    @GetMapping("/v1/user")
    fun getUser(@RequestBody @Valid userRequest: UserRequest): UserResponse {
        return userRequest.mapperToUserResponse(fetchUUID(), fetchEntries())
    }

    private fun fetchEntries(): ApiResponse? =
        openApi.getEntries()

    private fun fetchUUID(): UUID =
        idGenerator.generateId()
}