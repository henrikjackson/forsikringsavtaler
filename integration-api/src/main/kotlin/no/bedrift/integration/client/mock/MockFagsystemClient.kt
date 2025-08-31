package no.bedrift.integration.client.mock

import no.bedrift.integration.client.FagsystemClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Profile("mock")
class MockFagsystemClient : FagsystemClient {
    override fun opprettKunde(): UUID {
        return UUID.randomUUID()
    }

    override fun opprettAvtale(kundeId: String, produktkode: String): UUID {
        return UUID.randomUUID()
    }
}