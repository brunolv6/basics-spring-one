package com.basic.springone.port.facade

import com.amazonaws.services.kms.model.NotFoundException
import com.basic.springone.adapter.out.ApiResponse
import com.basic.springone.adapter.out.OpenApi
import com.basic.springone.objectmother.ApiObjectMother.customApiResponseEntry
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class OpenApiFacadeTest {
    private val openApi = mock<OpenApi>()

    private val openApiFacade = OpenApiFacade(
        openApi
    )

    @Test
    fun `test null when there is no data from api`() {

        openApi.stub {
            on {
                getEntries()
            }.thenReturn(null)
        }

        assertThat(openApiFacade.fetchEntries()).isNull()
    }

    @Test
    fun `test get null entries when there is 0 entries`() {
        val zeroEntriesApiResponse = ApiResponse(count = 0, entries = null)

        openApi.stub {
            on {
                getEntries()
            }.thenReturn(zeroEntriesApiResponse)
        }

        val apiResponseFacade = openApiFacade.fetchEntries()
        assertThat(apiResponseFacade).isNotNull
        assertThat(apiResponseFacade!!.count).isEqualTo(0)
        assertThat(apiResponseFacade.entries).isNull()
    }

    @Test
    fun `test get api-tipo-2 entry when there is 2 entries`() {
        val twoEntriesApiResponse = ApiResponse(
            count = 2,
            entries = listOf(
                customApiResponseEntry(),
                customApiResponseEntry()
            )
        )

        val expectedEntryForTwo = listOf(
            ApiResponse.Entry(
                api = "api-tipo-2",
                description = null,
                category = null
            )
        )

        openApi.stub {
            on {
                getEntries()
            }.thenReturn(twoEntriesApiResponse)
        }

        val apiResponseFacade = openApiFacade.fetchEntries()
        assertThat(apiResponseFacade).isNotNull
        assertThat(apiResponseFacade!!.count).isEqualTo(2)
        assertThat(apiResponseFacade.entries).isEqualTo(expectedEntryForTwo)
    }

    @Test
    fun `test throw NotFount when there is 3 entries`() {
        val threeEntriesApiResponse = ApiResponse(
            count = 3,
            entries = listOf(
                customApiResponseEntry(),
                customApiResponseEntry(),
                customApiResponseEntry()
            )
        )

        openApi.stub {
            on {
                getEntries()
            }.thenReturn(threeEntriesApiResponse)
        }

        assertThatThrownBy {
            openApiFacade.fetchEntries()
        }.isInstanceOf(NotFoundException::class.java)
    }
}