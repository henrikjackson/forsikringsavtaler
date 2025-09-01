package no.bedrift.integration.api.dto

import jakarta.validation.constraints.NotBlank
import no.bedrift.integration.avtale.AvtaleStatus
import java.util.*

data class OpprettAvtaleRequest(
    @field:NotBlank val kundeId: String,
    @field:NotBlank val produktkode: String
)

data class OpprettAvtaleResponse(
    val avtaleNummer: UUID? = null,
    val avtaleStatus: AvtaleStatus = AvtaleStatus.IKKE_AKTIVERT,
)
