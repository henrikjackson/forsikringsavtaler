package no.bedrift.integration.api

import no.bedrift.integration.api.dto.OpprettAvtaleResponse
import no.bedrift.integration.avtale.AvtaleStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvtaleTest(
    @Autowired val restTemplate: TestRestTemplate,
    @LocalServerPort val port: Int
) {

    private fun String.url() = "http://localhost:${port}${this}"

    @Test
    fun `POST opprett avtale - should return 201 Created with AVTALE_SENT and avtaleId`() {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val body = """{"kundeId":"123","produktkode":"ABC"}"""
        val request = HttpEntity(body, headers)

        val response = restTemplate.exchange(
            "/api/v1/avtaler/opprett".url(),
            HttpMethod.POST,
            request,
            OpprettAvtaleResponse::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        val responseBody = response.body!!
        assertThat(responseBody.avtaleStatus).isEqualTo(AvtaleStatus.AVTALE_SENT)
        assertThat(responseBody.avtaleNummer).isNotNull()
    }

    @Test
    fun `POST opprett avtale - validation error should return 400 with IKKE_AKTIVERT`() {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        // Tom kundeId trigger @NotBlank
        val body = """{"kundeId":"","produktkode":"ABC"}"""
        val request = HttpEntity(body, headers)

        val response = restTemplate.exchange(
            "/api/v1/avtaler/opprett".url(),
            HttpMethod.POST,
            request,
            OpprettAvtaleResponse::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        val responseBody = response.body!!
        assertThat(responseBody.avtaleStatus).isEqualTo(AvtaleStatus.IKKE_AKTIVERT)
    }

    @Test
    fun `POST opprett avtale - oppdatering status failure should return 500 with IKKE_AKTIVERT`() {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        // produktkode = "ERROR" tvinger oppdatering av avtaleStatus til å feile
        val body = """{"kundeId":"123","produktkode":"ERROR"}"""
        val request = HttpEntity(body, headers)

        val response = restTemplate.exchange(
            "/api/v1/avtaler/opprett".url(),
            HttpMethod.POST,
            request,
            OpprettAvtaleResponse::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
        val responseBody = response.body!!
        assertThat(responseBody.avtaleStatus).isEqualTo(AvtaleStatus.IKKE_AKTIVERT)
    }
}
