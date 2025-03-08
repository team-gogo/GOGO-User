package gogo.gogouser.domain.student.application.dto

import gogo.gogouser.domain.user.persistence.Sex
import java.time.LocalDateTime

data class StudentDto(
    val userId: Long,
    val studentId: Long,
    val schoolId: Long,
    val email: String,
    val name: String,
    val deviceToken: String?,
    val sex: Sex,
    val classNumber: Int,
    val studentNumber: Int,
    val isActiveProfanityFilter: Boolean,
    val createdAt: LocalDateTime
)

data class StudentInfoDto(
    val studentId: Long,
    val schoolId: Long,
    val sex: Sex,
    val name: String,
    val deviceToken: String?,
    val classNumber: Int,
    val studentNumber: Int,
)
