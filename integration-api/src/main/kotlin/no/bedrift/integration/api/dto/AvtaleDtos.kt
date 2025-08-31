package no.bedrift.integration.api.dto

import jakarta.validation.constraints.NotBlank
import java.util.*

data class OpprettAvtaleRequest(
    @field:NotBlank val kundeId: String,
    @field:NotBlank val produktkode: String
)

data class OpprettAvtaleResponse(
    val status: String,
    val avtaleId: UUID? = null,
    val melding: String? = null
)
