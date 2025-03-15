package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentDto
import gogo.gogouser.domain.student.application.dto.StudentInfoDto
import gogo.gogouser.domain.student.application.dto.StudentSearchDto

interface StudentService {
    fun queryByUserId(userId: Long): StudentDto
    fun queryBundle(studentIds: List<Long>): StudentBundleDto
    fun search(name: String): StudentSearchDto
    fun query(studentId: Long): StudentInfoDto
}
