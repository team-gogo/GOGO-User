package gogo.gogouser.domain.student.presentation

import gogo.gogouser.domain.student.application.StudentService
import gogo.gogouser.domain.student.application.dto.StudentDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class StudentController(
    private val studentService: StudentService,
) {

    @GetMapping("/student")
    fun queryByUserId(
        @RequestParam("userId") userId: Long
    ): ResponseEntity<StudentDto> {
        val response = studentService.queryByUserId(userId)
        return ResponseEntity.ok(response)
    }

}
