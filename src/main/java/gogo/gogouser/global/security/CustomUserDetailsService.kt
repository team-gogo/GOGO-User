package gogo.gogouser.global.security

import gogo.gogouser.domain.user.persistence.UserRepository
import gogo.gogouser.global.error.UserException
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails =
        CustomUserDetails(
            userRepository.findById(userId.toLong())
                .orElseThrow { UserException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()) }
        )
}