package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentDto

interface StudentService {
    fun queryByUserId(userId: Long): StudentDto
    fun queryBundle(studentIds: List<Long>): StudentBundleDto
}
