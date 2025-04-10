package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentInfoUpdateDto
import gogo.gogouser.domain.student.application.dto.StudentSearchDto
import gogo.gogouser.domain.student.persistence.Student

interface StudentService {
    fun queryByUserId(userId: Long): Student
    fun queryBundle(studentIds: List<Long>): StudentBundleDto
    fun search(name: String): StudentSearchDto
    fun update(userId: Long, dto: StudentInfoUpdateDto)
}
