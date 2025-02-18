package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthTokenDto
import gogo.gogouser.domain.user.application.UserProcessor
import gogo.gogouser.domain.user.application.UserReader
import gogo.gogouser.domain.user.persistence.User
import gogo.gogouser.global.external.GoogleLoginFeignClientService
import gogo.gogouser.global.jwt.JwtGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val authReader: AuthReader,
    private val authProcessor: AuthProcessor,
    private val userMapper: AuthMapper,
    private val userProcessor: UserProcessor,
    private val jwtGenerator: JwtGenerator,
    private val oauthService: GoogleLoginFeignClientService,
    private val userReader: UserReader
) : AuthService {

    @Transactional
    override fun login(dto: AuthLoginReqDto): AuthTokenDto {
        val email = oauthService.login(dto.oauthToken).email
        val user = userProcessor.getUserOrCreate(email)
        return generateToken(user)
    }

    @Transactional
    override fun refresh(token: String): AuthTokenDto {
        val refreshToken = authReader.read(token)
        val user = userReader.read(refreshToken.userId)
        return generateToken(user)
    }

    private fun generateToken(user: User): AuthTokenDto {
        val token = jwtGenerator.generateToken(
            userId = user.id.toString(),
            authority = user.authority,
        )
        authProcessor.saveRefreshToken(
            userId = user.id,
            token = token.refreshToken
        )
        return userMapper.map(token)
    }

}
