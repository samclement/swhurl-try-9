package com.swhurl.app

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Starts the app on a random port and calls it as the platform and a browser would.
@MicronautTest
class HttpTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `healthz answers ok`() {
        assertEquals("""{"ok":true}""", client.toBlocking().retrieve("/healthz"))
    }

    @Test
    fun `root says hello`() {
        assertTrue(client.toBlocking().retrieve("/").contains("hello from"))
    }
}
