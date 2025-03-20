package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto
import gogo.gogouser.domain.user.persistence.User
import gogo.gogouser.global.jwt.dto.TokenDto
import org.springframework.stereotype.Component

@Component
class AuthMapper {

    fun map(tokenDto: TokenDto) =
        AuthTokenDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

    fun login(tokenDto: AuthTokenDto, user: User) =
        AuthLoginDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken,
            authority = user.authority
        )

}
