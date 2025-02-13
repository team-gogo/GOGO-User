package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthLoginResDto

interface AuthService {
    fun login(dto: AuthLoginReqDto): AuthLoginResDto
}
