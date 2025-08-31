package no.bedrift.integration.client

import no.bedrift.integration.brev.BrevStatus
import java.util.UUID

interface BrevtjenesteClient {
    /**
     * Sender avtale til kunden og returnerer status på utsendelsen.
     */
    fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus
}