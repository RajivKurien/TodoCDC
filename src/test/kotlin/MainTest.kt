import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import server.main
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {

    @Ignore("W.I.P")
    @Test
    fun testRoot() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, world!", response.bodyAsText())
    }

    fun testModule1() = testApplication {
        application {
            this.main()
        }
    }

}