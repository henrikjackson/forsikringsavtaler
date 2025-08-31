package no.bedrift.integration.config

import no.bedrift.integration.api.dto.OpprettAvtaleResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<OpprettAvtaleResponse> {
        val msg = ex.bindingResult.allErrors.joinToString("; ") {
            val field = (it as? FieldError)?.field ?: "felt"
            "$field: ${it.defaultMessage}"
        }
        val body = OpprettAvtaleResponse(status = "FEIL", melding = msg)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception): ResponseEntity<OpprettAvtaleResponse> {
        val body = OpprettAvtaleResponse(status = "FEIL", melding = "Uventet feil")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}
