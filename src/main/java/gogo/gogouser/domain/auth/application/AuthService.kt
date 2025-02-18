package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto

interface AuthService {
    fun login(dto: AuthLoginReqDto): AuthTokenDto
    fun refresh(token: String): AuthTokenDto
}
