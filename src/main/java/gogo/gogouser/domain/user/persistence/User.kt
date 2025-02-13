package gogo.gogouser.domain.user.persistence

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
    val name: String? = null,

    @Column(name = "student_number", nullable = true)
    val studentNumber: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = true)
    val sex: Sex? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    val authority: Authority = Authority.UNAUTHENTICATED,

    @Column(name = "is_suspended", nullable = false)
    val isSuspended: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

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