package no.bedrift.integration.api

import jakarta.validation.Valid
import no.bedrift.integration.api.dto.OpprettAvtaleRequest
import no.bedrift.integration.api.dto.OpprettAvtaleResponse
import no.bedrift.integration.service.AvtaleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/avtaler")
class AvtaleController(
    private val avtaleService: AvtaleService
) {
    @PostMapping("/opprettAvtale")
    fun opprettAvtale(@Valid @RequestBody req: OpprettAvtaleRequest): ResponseEntity<OpprettAvtaleResponse> {
        val kunderNummer = avtaleService.opprettKunde()

        val avtaleNummer = avtaleService.opprettAvtale(kunderNummer.toString(), req.produktkode)

        val brevStatus = avtaleService.sendAvtaleTilKunde(req.produktkode, avtaleNummer)

        val avtaleStatus = avtaleService.oppdaterStatusTilAvtale(req.produktkode, avtaleNummer, brevStatus)

        val body = OpprettAvtaleResponse(status = "OK", avtaleId = avtaleNummer)

        return ResponseEntity.created(URI.create("/api/v1/avtaler/$avtaleNummer")).body(body)
    }
}
