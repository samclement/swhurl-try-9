package com.swhurl.app

import io.micronaut.context.annotation.Value
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.serde.annotation.Serdeable
import org.slf4j.LoggerFactory

@Serdeable
data class Health(val ok: Boolean)

@Serdeable
data class Hello(
    val message: String,
    val service: String,
    val email: String?,
)

@Controller
class HttpController(
    @Value("\${otel.service.name:swhurl-app}") private val serviceName: String,
) {
    private val log = LoggerFactory.getLogger(HttpController::class.java)

    // The platform's readiness and liveness probe. It needs nothing else: the app answers only once
    // started. Probe requests are not traced.
    @Get("/healthz")
    fun health(): Health = Health(true)

    // Sign-in happens before requests reach the app; X-Auth-Request-Email names the signed-in user.
    @Get("/")
    fun hello(@Header("X-Auth-Request-Email") email: String?): Hello {
        log.info("request handled")
        return Hello("hello from $serviceName", serviceName, email)
    }
}
