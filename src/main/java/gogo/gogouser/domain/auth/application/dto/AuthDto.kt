package gogo.gogouser.domain.auth.application.dto

import gogo.gogouser.domain.school.root.persistence.SchoolType
import gogo.gogouser.domain.user.persistence.Sex
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AuthLoginReqDto(
    @NotBlank
    val oauthToken: String,
    val deviceToken: String?
)

data class AuthSignUpReqDto(
    val deviceToken: String?,
    @NotBlank
    val name: String,
    @NotNull
    val classNumber: Int,
    @NotNull
    val studentNumber: Int,
    @NotNull
    val sex: Sex,
    @NotNull
    val school: SchoolInfoDto
)

data class SchoolInfoDto(
    @NotBlank
    val sdCode: String,
    @NotBlank
    val name: String,
    @NotNull
    val type: SchoolType,
    @NotBlank
    val address: String,
    @NotBlank
    val region: String,
    @NotNull
    val countOfStudent: Int,
    @NotNull
    val phoneNumber: String,
)

data class AuthTokenDto(
    val accessToken: String,
    val refreshToken: String
)
