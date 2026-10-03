# swhurl-try-9

kotlin cold-cache measurement, delete me

A Kotlin ([Micronaut](https://micronaut.io/)) app on the [swhurl platform](https://github.com/samclement/swhurl-platform), made from the [swhurl Kotlin template](https://github.com/samclement/swhurl-app-template-kotlin). Every push to `main` is checked, published to `ghcr.io/<owner>/swhurl-try-9` and deployed to staging; production changes through **Promote to production**. What the image must provide, the checks and the dependency updates: the [template's guide](https://github.com/samclement/swhurl-app-template-kotlin#readme). What the app needs from the platform: [`swhurl.yaml`](swhurl.yaml).

An HTTP service: `HttpController.kt` answers on port 8080; the platform probes `GET /healthz`.

## Local development

Needs a JDK 25 (Gradle downloads everything else):

```bash
./gradlew run                           # the agent stays off locally (the image loads it)
curl http://localhost:8080/healthz
./gradlew check                         # compile and tests, as CI does
```
