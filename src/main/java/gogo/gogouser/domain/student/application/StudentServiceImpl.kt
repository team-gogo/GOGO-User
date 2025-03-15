package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentDto
import gogo.gogouser.domain.student.application.dto.StudentInfoDto
import gogo.gogouser.domain.student.application.dto.StudentSearchDto
import gogo.gogouser.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    private val studentReader: StudentReader,
    private val studentMapper: StudentMapper,
    private val userUtil: UserUtil
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

    @Transactional(readOnly = true)
    override fun search(name: String): StudentSearchDto {
        val userId = userUtil.getCurrentUserId()
        val student = studentReader.readByUserId(userId)
        val searchStudents = studentReader.search(student.school.id, name)
        return StudentSearchDto(searchStudents)
    }

    @Transactional(readOnly = true)
    override fun query(studentId: Long): StudentInfoDto {
        val student = studentReader.read(studentId)
        return studentMapper.mapInfo(student)
    }

}
