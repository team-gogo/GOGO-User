package gogo.gogouser.domain.user.application

import gogo.gogouser.domain.user.persistence.User
import gogo.gogouser.domain.user.persistence.UserRepository
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun readByEmailOrNull(email: String): User? =
        userRepository.findByEmail(email)

}
