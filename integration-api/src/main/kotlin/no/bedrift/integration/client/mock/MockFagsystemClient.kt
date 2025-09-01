package no.bedrift.integration.client.mock

import no.bedrift.integration.avtale.AvtaleStatus
import no.bedrift.integration.brev.BrevStatus
import no.bedrift.integration.client.FagsystemClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Profile("mock")
class MockFagsystemClient : FagsystemClient {
    override fun opprettKunde(): UUID = UUID.randomUUID()

    override fun opprettAvtale(kundeId: String, produktkode: String): UUID = UUID.randomUUID()

    override fun oppdaterAvtaleStatus(kundeId: String, avtaleId: UUID, status: BrevStatus, skalFeile: Boolean?): AvtaleStatus {
        if (skalFeile!!) {
            throw IllegalStateException("Oppdatering av avtaleStatus har feilet")
        }

        return if (status == BrevStatus.SENT) {
            AvtaleStatus.AVTALE_SENT
        } else {
            AvtaleStatus.IKKE_AKTIVERT
        }
    }
}