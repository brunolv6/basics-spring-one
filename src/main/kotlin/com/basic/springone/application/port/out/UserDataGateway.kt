package com.basic.springone.application.port.out

import com.basic.springone.adapter.`in`.web.request.UserRequest

interface UserDataGateway {
    fun bulk(userRequest: UserRequest)
}