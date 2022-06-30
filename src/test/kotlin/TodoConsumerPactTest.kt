package com.zuhlke.todo.client

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import client.CreateTodoRequest
import client.CreateTodoResponse
import client.ZuhlkeClient
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "Provider", port = "1234")
class TodoApiPactConsumerTest {

    @BeforeEach
    fun setUp(mockServer: MockServer?) {
        assertThat(mockServer).isNotNull
    }

    @Pact(provider = "Provider", consumer = "TodoClient")
    fun createTodo(builder: PactDslWithProvider): RequestResponsePact {
        return builder
            .given("test state")
            .uponReceiving("ConsumerPactTest test interaction")
            .path("/todo")
            .method("POST")
            .willRespondWith()
            .status(200)
            .body("""{"id":"1"}""".trimIndent())
            .headers(mapOf("Content-Type" to "application/json"))
            .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "createTodo")
    fun testCreateTodo(mockServer: MockServer) = runBlocking {
        val todoClient = ZuhlkeClient(hostUrl = mockServer.getUrl())
        val response: CreateTodoResponse = todoClient.create(CreateTodoRequest(title = "Buy Cheese"))
        assertEquals("1", response.id)
    }
}