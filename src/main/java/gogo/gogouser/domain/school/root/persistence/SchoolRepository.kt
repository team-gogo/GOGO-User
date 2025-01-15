package gogo.gogouser.domain.school.root.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository: JpaRepository<School, Long> {
}
