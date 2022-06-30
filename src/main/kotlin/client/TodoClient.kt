package client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


interface TodoClient {

    suspend fun create(request: CreateTodoRequest): CreateTodoResponse

}

@Serializable
data class CreateTodoRequest(
    val title: String
)

@Serializable
data class CreateTodoResponse(
    val id: String
)

class ZuhlkeClient(
    engine: HttpClientEngine = CIO.create(),
    hostUrl: String,
) : TodoClient {
    val client: HttpClient
    val hostUrl: String

    init {
        this.hostUrl = hostUrl
        client = HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    override suspend fun create(request: CreateTodoRequest): CreateTodoResponse =
        client
            .post(urlString = "$hostUrl/todo") {
                setBody(request)
                contentType(ContentType.Application.Json)
            }
            .body()
}