package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginResDto
import gogo.gogouser.global.jwt.dto.TokenDto
import org.springframework.stereotype.Component

@Component
class AuthMapper {

    fun mapLoginRes(tokenDto: TokenDto) =
        AuthLoginResDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

}
