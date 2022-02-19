package com.basic.springone.ports.out

import com.basic.springone.ports.out.response.ApiResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url = "https://api.publicapis.org", name="openapi")
interface OpenApi {

    @GetMapping("/entries")
    fun getEntries(): ApiResponse?
}