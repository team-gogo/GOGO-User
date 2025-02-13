package gogo.gogouser.domain.school.root.application

import gogo.gogouser.domain.auth.application.dto.AuthSignUpReqDto
import gogo.gogouser.domain.school.detail.persistence.SchoolDetail
import gogo.gogouser.domain.school.detail.persistence.SchoolDetailRepository
import gogo.gogouser.domain.school.root.persistence.School
import gogo.gogouser.domain.school.root.persistence.SchoolRepository
import org.springframework.stereotype.Component

@Component
class SchoolProcessor(
    private val schoolReader: SchoolReader,
    private val schoolRepository: SchoolRepository,
    private val schoolDetailRepository: SchoolDetailRepository
) {

    fun getSchoolOrCreate(dto: AuthSignUpReqDto): School =
        schoolReader.readBySdCodeOrNull(dto.school.sdCode)
            ?: run {
                val createSchool = School.of(dto.school)
                val savedSchool = schoolRepository.save(createSchool)
                val schoolDetail = SchoolDetail.of(createSchool, dto.school)
                schoolDetailRepository.save(schoolDetail)
                savedSchool
            }

}
