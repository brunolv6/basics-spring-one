package com.basic.springone.adapter.`in`.web

import com.basic.springone.loadPayload
import com.basic.springone.objectmother.ApiObjectMother.defaultApiResponse
import com.basic.springone.port.facade.OpenApiFacade
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.stub
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.util.IdGenerator
import java.util.*

@WebMvcTest
class GetUserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var openApiFacade: OpenApiFacade

    @MockBean
    private lateinit var idGenerator: IdGenerator

    @Test
    fun `test get entries with success`() {
        val apiResponse = defaultApiResponse()

        openApiFacade.stub {
            on {
                fetchEntries()
            }.thenReturn(apiResponse)
        }

        idGenerator.stub {
            on {
                generateId()
            }.thenReturn(UUID.fromString("05d1b645-a902-45f8-94b9-e148926656bd"))
        }

        mockMvc.get("/v1/user") {
            contentType = MediaType.APPLICATION_JSON
            content = loadPayload("controller/v1_user_entry_request.json")
        }.andExpect {
            status { isOk() }
            content {
                json(loadPayload("controller/v1_user_response_success.json"))
            }
        }
    }

    @Test
    fun `test receive 404 when password is invalid`() {

        mockMvc.get("/v1/user") {
            contentType = MediaType.APPLICATION_JSON
            content = loadPayload("controller/v1_user_wrong_entry_request.json")
        }.andExpect {
            status { isBadRequest() }
        }
    }
}