package com.basic.springone.adapter.out

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url = "https://api.publicapis.org", name="openapi")
interface OpenApi {

    @GetMapping("/entries")
    fun getEntries(): ApiResponse?
}