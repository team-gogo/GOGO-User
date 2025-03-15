package gogo.gogouser.domain.student.persistence

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPQLQueryFactory
import gogo.gogouser.domain.student.application.dto.StudentSearchInfoDto
import gogo.gogouser.domain.student.persistence.QStudent.*
import org.springframework.stereotype.Repository

@Repository
class StudentCustomRepositoryImpl(
    private val queryFactory: JPQLQueryFactory
) : StudentCustomRepository {

    override fun search(schoolId: Long, name: String): List<StudentSearchInfoDto> {
        val builder = BooleanBuilder()
        builder.and(student.school.id.eq(schoolId))
        builder.and(student.user.name.containsIgnoreCase(name))

        return queryFactory.select(
            Projections.constructor(
                StudentSearchInfoDto::class.java,
                student.id,
                student.user.name,
                student.grade,
                student.classNumber,
                student.studentNumber
            )
        )
        .from(student)
        .where(builder)
        .limit(5)
        .fetch()
    }

}
