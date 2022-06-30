import io.ktor.server.engine.*
import io.ktor.server.netty.*
import server.configureSerialization
import server.main

fun main(args: Array<String>) {
    val env = applicationEngineEnvironment {
        module {
            configureSerialization()
            main()
        }
        // Public API
        connector {
            host = "0.0.0.0"
            port = 8080
        }
    }
    embeddedServer(Netty, env).start(true)
}

