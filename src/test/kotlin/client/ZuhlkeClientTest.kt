package client

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ZuhlkeClientTest {

    @Test
    internal fun createTodoRequestRespondsWithId() = runBlocking {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""{"id":"1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = ZuhlkeClient(engine = mockEngine, hostUrl = "")

        assertEquals(
            CreateTodoResponse(id = "1"),
            client.create(CreateTodoRequest("Buy Cheese"))
        )
    }
}