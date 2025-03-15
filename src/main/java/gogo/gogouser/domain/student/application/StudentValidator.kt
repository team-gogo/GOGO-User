package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.school.root.persistence.School
import gogo.gogouser.domain.student.persistence.StudentRepository
import gogo.gogouser.global.error.UserException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class StudentValidator(
    private val studentRepository: StudentRepository
) {

    fun validDuplicate(schoolId: Long, grade: Int, classNumber: Int, studentNumber: Int) {
        val isDuplicate = studentRepository.existsBySchoolIdAndGradeAndClassNumberAndStudentNumber(
            schoolId = schoolId,
            grade = grade,
            classNumber = classNumber,
            studentNumber = studentNumber
        )

        if (isDuplicate) {
            throw UserException("동일한 학년, 반, 번호를 가진 학생이 존재합니다.", HttpStatus.CONFLICT.value())
        }
    }

}
