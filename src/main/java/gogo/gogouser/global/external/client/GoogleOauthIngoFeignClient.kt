package gogo.gogouser.global.external.client

import gogo.gogouser.global.external.dto.GoogleInfoResDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "GoogleOauthInfoClient", url = "\${oauth.google.info-url}")
interface GoogleOauthInfoFeignClient {
    @GetMapping("/oauth2/v1/userinfo")
    fun getInfo(
        @RequestHeader("Authorization") accessToken: String
    ): GoogleInfoResDto
}
