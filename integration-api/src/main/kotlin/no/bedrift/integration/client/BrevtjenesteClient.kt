package no.bedrift.integration.client

import no.bedrift.integration.brev.BrevStatus
import java.util.UUID

interface BrevtjenesteClient {

    fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus
}