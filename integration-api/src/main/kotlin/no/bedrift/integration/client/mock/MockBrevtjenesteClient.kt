package no.bedrift.integration.client.mock

import no.bedrift.integration.brev.BrevStatus
import no.bedrift.integration.client.BrevtjenesteClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.random.Random

@Component
@Profile("mock")
class MockBrevtjenesteClient : BrevtjenesteClient {
    override fun sendAvtaleTilKunde(kundeId: String, avtaleId: UUID): BrevStatus {
        // Enkel mock: 90% SENT, 10% FAILED — juster etter behov
        return if (Random.nextDouble() < 0.9) BrevStatus.SENT else BrevStatus.FAILED
    }
}