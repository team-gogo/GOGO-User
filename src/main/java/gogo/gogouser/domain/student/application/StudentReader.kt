package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentSearchInfoDto
import gogo.gogouser.domain.student.persistence.Student
import gogo.gogouser.domain.student.persistence.StudentRepository
import gogo.gogouser.global.error.UserException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class StudentReader(
    private val studentRepository: StudentRepository
) {
    fun readByUserId(userId: Long): Student =
        studentRepository.findByUserId(userId)
            ?: throw UserException("Not Found Student user id = $userId", HttpStatus.NOT_FOUND.value())

    fun search(schoolId: Long, name: String): List<StudentSearchInfoDto> =
        studentRepository.search(schoolId, name)

    fun readByIds(studentIds: List<Long>): List<Student> = studentRepository.findByIds(studentIds)

}
