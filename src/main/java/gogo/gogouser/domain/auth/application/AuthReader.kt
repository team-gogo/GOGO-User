package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.persistence.RefreshTokenRepository
import gogo.gogouser.global.error.UserException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class AuthReader(
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun read(token: String) =
        refreshTokenRepository.findByToken(token)
            ?: throw UserException("RefreshToken Not Found, token: $token", HttpStatus.NOT_FOUND.value())

}
