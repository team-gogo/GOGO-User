package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentDto
import gogo.gogouser.domain.student.application.dto.StudentInfoDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    private val studentReader: StudentReader,
    private val studentMapper: StudentMapper
) : StudentService {

    @Transactional(readOnly = true)
    override fun queryByUserId(userId: Long): StudentDto {
        val student = studentReader.readByUserId(userId)
        return studentMapper.map(student)
    }

    @Transactional(readOnly = true)
    override fun queryBundle(studentIds: List<Long>): StudentBundleDto {
        val students = studentReader.readByIds(studentIds)
        return studentMapper.mapStudents(students)
    }

}
