package no.bedrift.integration.service

import no.bedrift.integration.avtale.AvtaleStatus
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
    fun opprettKunde(): UUID = fagsystemClient.opprettKunde()

    fun opprettAvtale(kundeId: String, produktkode: String): UUID = fagsystemClient.opprettAvtale(kundeId, produktkode)

    fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus {
        val status = brevtjenesteClient.sendAvtaleTilKunde(kundeId, avtaleId)

        // Her burde det har vært en form for retry-mekanisme / håndtering av alle utsendelser med status != SENT
        if (status != BrevStatus.SENT) {
            throw IllegalStateException("Brevutsendelse for avtale $avtaleId feilet")
        }

        return status
    }

    fun oppdaterStatusTilAvtale(kundeId: String, avtaleId: UUID, status: BrevStatus, skalFeile: Boolean? = false): AvtaleStatus = try {
        fagsystemClient.oppdaterAvtaleStatus(kundeId, avtaleId, status, skalFeile)
    } catch (e: Exception) {
        throw IllegalStateException("Oppdatering av avtale $avtaleId feilet", e)
    }
}
