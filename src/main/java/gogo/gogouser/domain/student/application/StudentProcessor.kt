package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentInfoUpdateDto
import gogo.gogouser.domain.student.persistence.Student
import gogo.gogouser.domain.student.persistence.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentProcessor(
    private val studentRepository: StudentRepository,
) {

    fun update(student: Student, dto: StudentInfoUpdateDto) {
        student.update(dto)
        studentRepository.save(student)
    }

}
