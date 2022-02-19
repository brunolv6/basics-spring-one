package com.basic.springone.objectmother

import com.basic.springone.ports.out.response.ApiResponse
import com.basic.springone.ports.out.response.ApiResponse.Entry

object ApiObjectMother {

    fun defaultApiResponse() =
        ApiResponse(
            count = 2,
            entries = listOf(
                Entry(
                    api = "api_1",
                    description = "descricao_1",
                    category = "categoria_1"
                ),
                Entry(
                    api = "api_2",
                    description = "descricao_2",
                    category = "categoria_2"
                ),
            )
        )
}