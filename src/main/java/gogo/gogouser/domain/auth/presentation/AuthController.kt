package gogo.gogouser.domain.auth.presentation

import gogo.gogouser.domain.auth.application.AuthService
import gogo.gogouser.domain.auth.application.dto.AuthLoginDto
import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthSignUpReqDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto
import gogo.gogouser.domain.user.persistence.Authority
import gogo.gogouser.global.jwt.JwtGenerator
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtGenerator: JwtGenerator,
) {

    @PostMapping("/test-login/{user_id}")
    fun test(
        @PathVariable("user_id") userId: Long,
    ) = jwtGenerator.generateToken(userId.toString(), Authority.USER)

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid dto: AuthLoginReqDto
    ): ResponseEntity<AuthLoginDto> {
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

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid dto: AuthSignUpReqDto
    ): ResponseEntity<Void> {
        authService.signup(dto)
        return ResponseEntity.ok().build()
    }

}
