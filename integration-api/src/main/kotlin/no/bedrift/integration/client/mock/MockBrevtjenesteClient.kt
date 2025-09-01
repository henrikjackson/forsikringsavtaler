package no.bedrift.integration.client.mock

import no.bedrift.integration.brev.BrevStatus
import no.bedrift.integration.client.BrevtjenesteClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Profile("mock")
class MockBrevtjenesteClient : BrevtjenesteClient {
    override fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus = BrevStatus.SENT
}