package gogo.gogouser.domain.school.root.persistence

import jakarta.persistence.*

@Entity
@Table(name = "tbl_school")
class School(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "school_name", nullable = false)
    val schoolName: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "school_type", nullable = false)
    val schoolType: SchoolType
) {
}

enum class SchoolType {
    MIDDLE_SCHOOL, HIGH_SCHOOL
}
