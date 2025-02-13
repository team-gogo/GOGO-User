package gogo.gogouser.domain.auth.presentation

import gogo.gogouser.domain.auth.application.AuthService
import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthLoginResDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid dto: AuthLoginReqDto
    ): ResponseEntity<AuthLoginResDto> {
        val response = authService.login(dto)
        return ResponseEntity.ok(response)
    }

}
