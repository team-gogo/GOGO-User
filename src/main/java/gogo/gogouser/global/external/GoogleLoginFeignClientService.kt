package gogo.gogouser.global.external

import gogo.gogouser.global.external.client.GoogleOauthInfoFeignClient
import gogo.gogouser.global.external.dto.GoogleInfoResDto
import org.springframework.stereotype.Service

@Service
class GoogleLoginFeignClientService(
    private val googleOauthInfoFeignClient: GoogleOauthInfoFeignClient
) {

    fun login(accessToken: String): GoogleInfoResDto {
        val infoDto: GoogleInfoResDto

        try {
            infoDto = getInfo(accessToken)
        } catch (e: Exception) {
            throw RuntimeException("google oauth 사용자 정보 요청 중 예외가 발생했습니다.")
        }

        return infoDto
    }

    private fun getInfo(accessToken: String) =
        googleOauthInfoFeignClient.getInfo(
            accessToken = "Bearer $accessToken"
        )

}
