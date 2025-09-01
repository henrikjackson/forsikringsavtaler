package no.bedrift.integration.client

import no.bedrift.integration.avtale.AvtaleStatus
import no.bedrift.integration.brev.BrevStatus
import java.util.UUID

interface FagsystemClient {

    fun opprettKunde(): UUID

    fun opprettAvtale(kundeId: String, produktkode: String): UUID

    fun oppdaterAvtaleStatus(kundeId: String, avtaleId: UUID, status: BrevStatus, skalFeile: Boolean? = false): AvtaleStatus
}