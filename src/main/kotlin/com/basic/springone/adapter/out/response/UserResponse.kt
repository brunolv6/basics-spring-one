package com.basic.springone.adapter.out.response

import java.util.UUID

data class UserResponse(
    val uuid: UUID? = UUID.randomUUID(),
    val emailWithPassword: String,
    val entriesSize: Int,
    val entries: List<EntryResponse>?
)

