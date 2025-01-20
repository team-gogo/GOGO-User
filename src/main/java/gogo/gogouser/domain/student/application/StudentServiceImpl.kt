package gogo.gogouser.domain.student.application

import gogo.gogouser.domain.student.application.dto.StudentDto
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

}
