package server

import client.CreateTodoResponse
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.main() {
    routing {
        get("/") {
            call.respondText("Connected to public api")
        }

        post("/todo") {
            call.respond(CreateTodoResponse(id = "1"))
        }
    }
}