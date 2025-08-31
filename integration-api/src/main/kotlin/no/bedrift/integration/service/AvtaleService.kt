package no.bedrift.integration.service

import no.bedrift.integration.brev.BrevStatus
import no.bedrift.integration.client.BrevtjenesteClient
import no.bedrift.integration.client.FagsystemClient
import org.springframework.stereotype.Service
import java.util.*

@Service
class AvtaleService(
    private val fagsystemClient: FagsystemClient,
    private val brevtjenesteClient: BrevtjenesteClient
) {
    fun opprettKunde(): UUID {
        return fagsystemClient.opprettKunde()
    }

    fun opprettAvtale(kundeId: String, produktkode: String): UUID {
        return fagsystemClient.opprettAvtale(kundeId, produktkode)
    }

    fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus {
        return brevtjenesteClient.sendAvtaleTilKunde(kundeId, avtaleId)
    }

    fun oppdaterStatusTilAvtale(kundeId: String, avtaleId: UUID, status: BrevStatus): BrevStatus {
        return fagsystemClient.oppdaterAvtaleStatus(kundeId, avtaleId, status)
    }
}
