package no.bedrift.integration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntegrationApiApplication

fun main(args: Array<String>) {
    runApplication<IntegrationApiApplication>(*args)
}
