package com.basic.springone.adapter.`in`.web

import com.basic.springone.adapter.`in`.web.request.UserRequest
import com.basic.springone.adapter.`in`.web.response.UserResponse
import com.basic.springone.adapter.out.OpenApi
import com.basic.springone.adapter.out.ApiResponse
import com.basic.springone.port.facade.OpenApiFacade
import org.springframework.util.IdGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class GetUserController(
    private val idGenerator: IdGenerator,
    private val openApiFacade: OpenApiFacade
) {

    @GetMapping("/v1/user")
    fun getUser(@RequestBody @Valid userRequest: UserRequest): UserResponse {
        return userRequest.mapperToUserResponse(fetchUUID(), fetchEntries())
    }

    private fun fetchEntries(): ApiResponse? =
        openApiFacade.fetchEntries()

    private fun fetchUUID(): UUID =
        idGenerator.generateId()
}