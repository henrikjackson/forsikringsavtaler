package no.bedrift.fag.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceholderController {
    @GetMapping("/api/v1/fag/health")
    fun health() = mapOf("status" to "OK")
}
