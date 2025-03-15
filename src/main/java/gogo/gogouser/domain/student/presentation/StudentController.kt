package gogo.gogouser.domain.student.presentation

import gogo.gogouser.domain.student.application.StudentMapper
import gogo.gogouser.domain.student.application.StudentService
import gogo.gogouser.domain.student.application.dto.StudentBundleDto
import gogo.gogouser.domain.student.application.dto.StudentDto
import gogo.gogouser.domain.student.application.dto.StudentInfoDto
import gogo.gogouser.domain.student.application.dto.StudentSearchDto
import gogo.gogouser.global.util.UserUtil
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class StudentController(
    private val studentService: StudentService,
    private val userUtil: UserUtil,
    private val studentMapper: StudentMapper,
) {

    @GetMapping("/student")
    fun queryByUserId(
        @RequestParam("userId") userId: Long
    ): ResponseEntity<StudentDto> {
        val student = studentService.queryByUserId(userId)
        val response = studentMapper.map(student)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/student/bundle")
    fun queryBundle(
        @RequestParam("studentIds") studentIs: List<Long>
    ): ResponseEntity<StudentBundleDto> {
        val response = studentService.queryBundle(studentIs)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/student/search")
    fun search(
        @RequestParam("name") @Valid @NotBlank name: String
    ): ResponseEntity<StudentSearchDto> {
        val response = studentService.search(name)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/student/me")
    fun me(): ResponseEntity<StudentInfoDto> {
        val userId = userUtil.getCurrentUserId()
        val student = studentService.queryByUserId(userId)
        val response = studentMapper.mapInfo(student)
        return ResponseEntity.ok(response)
    }

}
