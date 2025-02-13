package gogo.gogouser.domain.user.application

import gogo.gogouser.domain.user.application.dto.UserLoginDto

interface UserService {
    fun login(dto: UserLoginDto)
}
