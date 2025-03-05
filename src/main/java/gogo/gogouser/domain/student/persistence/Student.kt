package gogo.gogouser.domain.student.persistence

import gogo.gogouser.domain.auth.application.dto.AuthSignUpReqDto
import gogo.gogouser.domain.school.root.persistence.School
import gogo.gogouser.domain.user.persistence.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: User,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    val school: School,

    @Column(name = "device_token", nullable = true)
    val deviceToken: String?,

    @Column(name = "grade", nullable = false)
    val grade: Int,

    @Column(name = "class_number", nullable = false)
    val classNumber: Int,

    @Column(name = "student_number", nullable = false)
    val studentNumber: Int,

    @Column(name = "is_active_profanity_filter", nullable = false)
    val isActiveProfanityFilter: Boolean = false,
) {
    companion object {

        fun of(user: User, school: School, dto: AuthSignUpReqDto) =
            Student(
                user = user,
                school = school,
                deviceToken = dto.deviceToken,
                grade = dto.grade,
                classNumber = dto.classNumber,
                studentNumber = dto.studentNumber,
            )

    }
}
