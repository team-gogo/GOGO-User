package gogo.gogouser.domain.user.application

import gogo.gogouser.domain.user.application.dto.UserLoginDto
import gogo.gogouser.domain.user.persistence.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {

    @Transactional
    override fun login(dto: UserLoginDto) {
        TODO("Not yet implemented")
    }

}
