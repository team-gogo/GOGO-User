package gogo.gogouser.domain.user.application

import gogo.gogouser.domain.user.persistence.User
import gogo.gogouser.domain.user.persistence.UserRepository
import org.springframework.stereotype.Component

@Component
class UserProcessor(
    private val userReader: UserReader,
    private val userRepository: UserRepository,
) {

    fun getUserOrCreate(email: String): User =
        userReader.readByEmailOrNull(email)
            ?: userRepository.save(User.of(email))

}
