package com.basic.springone.adapter.`in`.web.response

import java.util.UUID

data class UserResponse(
    val uuid: UUID,
    val emailWithPassword: String,
    val entriesSize: Int,
    val entries: List<EntryResponse>?
)

