package gogo.gogouser.domain.school.detail.persistence

import gogo.gogouser.domain.auth.application.dto.SchoolInfoDto
import gogo.gogouser.domain.school.root.persistence.School
import jakarta.persistence.*

@Entity
@Table(name = "tbl_school_detail")
class SchoolDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false, unique = true)
    val school: School,

    @Column(name = "address", nullable = false)
    val address: String,

    @Column(name = "region", nullable = false)
    val region: String,

    @Column(name = "phoneNumber", nullable = false)
    val phoneNumber: String,
) {

    companion object {

        fun of (school: School, dto: SchoolInfoDto) =
            SchoolDetail(
                school = school,
                address = dto.address,
                region = dto.region,
                phoneNumber = dto.phoneNumber,
            )

    }

}
