package gogo.gogouser.domain.school.detail.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_school_detail")
class SchoolDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_detail_id")
    val schoolDetailId: Long = 0,

    @Column(name = "school_id", nullable = false, unique = true)
    val schoolId: Long,

    @Column(name = "address", nullable = false)
    val address: String,

    @Column(name = "region", nullable = false)
    val region: String,

    @Column(name = "count_of_student", nullable = false)
    val countOfStudent: Int,

    @Column(name = "phoneNumber", nullable = false)
    val phoneNumber: String,
) {
}
