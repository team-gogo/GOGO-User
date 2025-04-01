package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginDto
import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthSignUpReqDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto

interface AuthService {
    fun login(dto: AuthLoginReqDto): AuthLoginDto
    fun refresh(token: String): AuthTokenDto
    fun signup(dto: AuthSignUpReqDto): AuthTokenDto
}
