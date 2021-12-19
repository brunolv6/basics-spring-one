package com.basic.springone.adapter.`in`.web

import com.basic.springone.adapter.`in`.request.UserRequest
import com.basic.springone.adapter.`in`.response.UserResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GetUserController {

    @GetMapping("/v1/user")
    fun getUser(@RequestBody userRequest: UserRequest): UserResponse =
        UserResponse(emailWithPassword = userRequest.toString())
}