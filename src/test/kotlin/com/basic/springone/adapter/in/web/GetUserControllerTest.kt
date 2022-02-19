package com.basic.springone.adapter.`in`.web

import com.basic.springone.loadPayload
import com.basic.springone.objectmother.ApiObjectMother.defaultApiResponse
import com.basic.springone.ports.out.OpenApi
import org.junit.jupiter.api.Test
import org.mockito.kotlin.stub
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
    private lateinit var openApi: OpenApi

    @MockBean
    private lateinit var idGenerator: IdGenerator

    @Test
    fun `test receive entries with success`() {
        val apiResponse = defaultApiResponse()

        openApi.stub {
            on {
                getEntries()
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
    fun `test wrong request must receive 404`() {

        mockMvc.get("/v1/user") {
            contentType = MediaType.APPLICATION_JSON
            content = loadPayload("controller/v1_user_wrong_entry_request.json")
        }.andExpect {
            status { isBadRequest() }
        }
    }
}