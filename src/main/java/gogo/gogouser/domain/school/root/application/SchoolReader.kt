package gogo.gogouser.domain.school.root.application

import gogo.gogouser.domain.school.root.persistence.School
import gogo.gogouser.domain.school.root.persistence.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolReader(
    private val schoolRepository: SchoolRepository
) {

    fun readBySdCodeOrNull(sdCode: String): School? = schoolRepository.findBySdCode(sdCode)

}
