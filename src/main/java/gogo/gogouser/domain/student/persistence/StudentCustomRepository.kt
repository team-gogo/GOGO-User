package gogo.gogouser.domain.student.persistence

import gogo.gogouser.domain.student.application.dto.StudentSearchInfoDto

interface StudentCustomRepository {
    fun search(schoolId: Long, name: String): List<StudentSearchInfoDto>
}
