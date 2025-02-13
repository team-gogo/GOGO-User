package gogo.gogouser.domain.auth.application

import gogo.gogouser.domain.auth.application.dto.AuthLoginReqDto
import gogo.gogouser.domain.auth.application.dto.AuthLoginResDto
import gogo.gogouser.domain.user.application.UserProcessor
import gogo.gogouser.global.external.GoogleLoginFeignClientService
import gogo.gogouser.global.jwt.JwtGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val userProcessor: UserProcessor,
    private val userMapper: AuthMapper,
    private val authProcessor: AuthProcessor,
    private val jwtGenerator: JwtGenerator,
    private val oauthService: GoogleLoginFeignClientService
) : AuthService {

    @Transactional
    override fun login(dto: AuthLoginReqDto): AuthLoginResDto {
        val email = oauthService.login(dto.oauthToken).email
        val user = userProcessor.getUserOrCreate(email)
        val token = jwtGenerator.generateToken(
            userId = user.id.toString(),
            authority = user.authority,
        )
        authProcessor.saveRefreshToken(
            userId = user.id,
            token = token.refreshToken
        )

        return userMapper.mapLoginRes(token)
    }

}
