package gogo.gogouser.domain.user.persistence

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long = 0,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "student_number", nullable = false)
    val studentNumber: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    val sex: Sex,

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    val authority: Authority,

    @Column(name = "is_suspended", nullable = false)
    val isSuspended: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
}

enum class Sex {
    MALE, FEMALE
}

enum class Authority {
    UNAUTHENTICATED, USER, STAFF, DEVELOPER
}