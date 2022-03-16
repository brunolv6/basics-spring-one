package com.basic.springone.objectmother

import com.basic.springone.adapter.out.ApiResponse
import com.basic.springone.adapter.out.ApiResponse.Entry

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

    fun customApiResponseEntry() =
        ApiResponse.Entry(
            api = "customApi",
            description = "descricao_1",
            category = "categoria_1"
        )
}