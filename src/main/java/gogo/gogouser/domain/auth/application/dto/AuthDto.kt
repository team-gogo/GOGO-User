package gogo.gogouser.domain.auth.application.dto

import jakarta.validation.constraints.NotBlank

data class AuthLoginReqDto(
    @NotBlank
    val oauthToken: String,
    val deviceToken: String?
)

data class AuthLoginResDto(
    val accessToken: String,
    val refreshToken: String
)
