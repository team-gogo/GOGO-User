package gogo.gogouser.domain.user.application.dto

data class UserLoginDto(
    val oauthToken: String,
    val deviceToken: String?
)
