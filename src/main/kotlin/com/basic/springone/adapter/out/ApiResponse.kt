package com.basic.springone.adapter.out

import com.basic.springone.adapter.`in`.web.response.EntryResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResponse(
    val count: Int,
    val entries: List<Entry>?
) {
    data class Entry(
        @JsonProperty(value = "API")
        val api: String?,
        @JsonProperty(value = "Description")
        val description: String?,
        @JsonProperty(value = "Category")
        val category: String?
    ) {
        fun mapperToEntryResponse() =
            EntryResponse(
                api = api,
                description = description,
                category = category
            )
    }
}
