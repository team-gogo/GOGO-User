package gogo.gogouser.domain.school.detail.persistence

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

    @Column(name = "count_of_student", nullable = false)
    val countOfStudent: Int,

    @Column(name = "phoneNumber", nullable = false)
    val phoneNumber: String,
) {
}
