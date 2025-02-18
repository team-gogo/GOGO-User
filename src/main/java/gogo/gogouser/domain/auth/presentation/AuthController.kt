package gogo.gogouser.domain.auth.presentation

import gogo.gogouser.domain.auth.application.AuthService
import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid dto: AuthLoginReqDto
    ): ResponseEntity<AuthTokenDto> {
        val response = authService.login(dto)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/refresh")
    fun login(
        @RequestHeader("Refresh-Token") token: String,
    ): ResponseEntity<AuthTokenDto> {
        val response = authService.refresh(token)
        return ResponseEntity.ok(response)
    }

}
