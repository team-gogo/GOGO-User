package gogo.gogouser.domain.user.persistence

import gogo.gogouser.domain.auth.application.dto.AuthSignUpReqDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "name", nullable = true)
    var name: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = true)
    var sex: Sex? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    var authority: Authority = Authority.UNAUTHENTICATED,

    @Column(name = "is_suspended", nullable = false)
    var isSuspended: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    fun signUp(dto: AuthSignUpReqDto) {
        name = dto.name
        sex = dto.sex
        authority = Authority.USER
    }

    fun update(name: String, sex: Sex) {
        this.name = name
        this.sex = sex
    }

    companion object {
        fun of(email: String) = User(email = email)
    }

}

enum class Sex {
    MALE, FEMALE
}

enum class Authority {
    UNAUTHENTICATED, USER, STAFF, DEVELOPER
}
