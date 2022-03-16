package com.basic.springone.port.facade

import com.amazonaws.services.kms.model.NotFoundException
import com.basic.springone.adapter.out.ApiResponse
import com.basic.springone.adapter.out.OpenApi
import org.springframework.stereotype.Service

@Service
class OpenApiFacade(
    private val openApi: OpenApi
) {

    fun fetchEntries(): ApiResponse? {
        val entries = getEntries() ?: return null

        if (entries.count == 0) return entries

        return when (entries.count) {
            2 -> ApiResponse(count = 2, entries = listOf(ApiResponse.Entry("api-tipo-2", null, null)))
            3 -> throw NotFoundException("Sem Retorno")
            else -> ApiResponse(count = 3, listOf(ApiResponse.Entry("api-tipo-3", null, null)))
        }
    }

    private fun getEntries(): ApiResponse? =
        openApi.getEntries()
}