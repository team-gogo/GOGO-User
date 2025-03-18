package gogo.gogouser.domain.student.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StudentRepository: JpaRepository<Student, Long>, StudentCustomRepository {
    fun findByUserId(userId: Long): Student?

    @Query("SELECT s FROM Student s WHERE s.id in (:ids)")
    fun findByIds(ids: List<Long>): List<Student>
}
