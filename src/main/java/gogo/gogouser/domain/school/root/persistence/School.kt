package gogo.gogouser.domain.school.root.persistence

import gogo.gogouser.domain.auth.application.dto.SchoolInfoDto
import gogo.gogouser.domain.school.detail.persistence.SchoolDetail
import jakarta.persistence.*

@Entity
@Table(name = "tbl_school")
class School(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "sd_code", unique = true, nullable = false)
    val sdCode: String,

    @Column(name = "school_name", nullable = false)
    val schoolName: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "school_type", nullable = false)
    val schoolType: SchoolType,

    @OneToOne(cascade = [(CascadeType.ALL)], mappedBy = "school")
    @JoinColumn(name = "school_detail_id")
    val schoolDetail: SchoolDetail? = null,
) {

    companion object {

        fun of(dto: SchoolInfoDto)=
            School(
                sdCode = dto.sdCode,
                schoolName = dto.name,
                schoolType = dto.type,
            )

    }

}

enum class SchoolType {
    MIDDLE_SCHOOL, HIGH_SCHOOL
}
