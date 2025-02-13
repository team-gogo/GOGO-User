package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.persistence.RefreshToken
import gogo.gogouser.domain.auth.persistence.RefreshTokenRepository
import gogo.gogouser.global.jwt.JwtProperties
import org.springframework.stereotype.Component

@Component
class AuthProcessor(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun saveRefreshToken(userId: Long, token: String) {
        val refreshToken = RefreshToken(
            userId = userId,
            token = token,
            expirationTime = jwtProperties.refreshExp.toInt()
        )
        refreshTokenRepository.save(refreshToken)
    }

}
