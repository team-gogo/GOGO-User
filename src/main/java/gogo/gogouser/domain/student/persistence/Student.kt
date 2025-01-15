package gogo.gogouser.domain.student.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_student")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    val studentId: Long = 0,

    @Column(name = "user_id", nullable = false, unique = true)
    val userId: Long,

    @Column(name = "school_id", nullable = false)
    val schoolId: Long,

    @Column(name = "device_token", nullable = true)
    val deviceToken: String?,

    @Column(name = "class_number", nullable = false)
    val classNumber: Int,

    @Column(name = "is_active_profanity_filter", nullable = false)
    val isActiveProfanityFilter: Boolean = false,
) {
}
