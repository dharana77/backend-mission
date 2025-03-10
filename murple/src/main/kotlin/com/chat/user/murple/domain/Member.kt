package com.chat.user.murple.domain

import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.enums.Gender
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor

@Entity
@Table(name = "members")
@AllArgsConstructor
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 1024)
    var name: String,
    var age: Int? = null,
    @Column(length = 1024)
    var email: String? = null,
    var gender: Gender? = null,
    var number: String? = null,
    var isNumberVerified: Boolean? = false,
    var countryCode: String? = null,
    @Column(nullable = false, length = 1024)
    var address: String? = null,

) : BaseEntity() {
    fun update(updateRequest: InUpdateMember) {
        name = updateRequest.name
        age = updateRequest.age
        email = updateRequest.email
        gender = Gender.valueOf(updateRequest.gender)
        number = updateRequest.number
        isNumberVerified = updateRequest.isVerified
        countryCode = updateRequest.countryCode
        address = updateRequest.address
    }
}
