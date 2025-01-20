package gogo.gogouser.domain.student.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
    fun findByUserId(userId: Long): Student?
}
