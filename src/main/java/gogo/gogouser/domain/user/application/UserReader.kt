package gogo.gogouser.domain.user.application

import gogo.gogouser.domain.user.persistence.User
import gogo.gogouser.domain.user.persistence.UserRepository
import gogo.gogouser.global.error.UserException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun readByEmailOrNull(email: String): User? =
        userRepository.findByEmail(email)

    fun read(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?: throw UserException("User Not Found, id = $userId", HttpStatus.NOT_FOUND.value())

}
