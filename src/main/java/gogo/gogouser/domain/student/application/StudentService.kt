package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentDto
import gogo.gogouser.domain.student.application.dto.StudentInfoDto

interface StudentService {
    fun queryByUserId(userId: Long): StudentDto
    fun queryBundle(studentIds: List<Long>): List<StudentInfoDto>
}
