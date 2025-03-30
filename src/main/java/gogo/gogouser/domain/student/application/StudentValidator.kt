package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.persistence.Student
import gogo.gogouser.domain.student.persistence.StudentRepository
import gogo.gogouser.global.error.UserException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class StudentValidator(
    private val studentRepository: StudentRepository
) {

    fun validUpdate(student: Student, grade: Int, classNumber: Int, studentNumber: Int) {
        if (
            student.grade != grade ||
            student.classNumber != classNumber ||
            student.studentNumber != studentNumber
        ) {
            validDuplicate(student.school.id, grade, classNumber, studentNumber)
        }
    }

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
