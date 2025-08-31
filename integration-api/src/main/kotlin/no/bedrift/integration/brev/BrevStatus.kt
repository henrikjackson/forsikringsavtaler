package no.bedrift.integration.brev

enum class BrevStatus {
    SENT,       // sendt OK
    QUEUED,     // lagt i kø (ok for demo)
    FAILED      // feilet utsendelse
}