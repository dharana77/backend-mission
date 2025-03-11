package com.chat.user.murple.domain

import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.enums.Gender
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor

@Entity
@Table(name = "members")
@AllArgsConstructor
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false) @field:Size(min = 1, max = 1024, message = "이름은 최소 1글자에서 1024글자 사이여야 합니다.")
    var name: String,

    var age: Int? = null,

    @Column(length = 1024)  @field:Email(message = "올바르지 않은 이메일 형식입니다.")
    var email: String? = null,

    var gender: Gender? = null,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var phones: MutableList<MemberPhone> = mutableListOf(),

    @Column(nullable = false, length = 1024)
    var address: String? = null,

) : BaseEntity() {
    fun update(updateRequest: InUpdateMember) {
        name = updateRequest.name
        age = updateRequest.age
        email = updateRequest.email
        gender = Gender.valueOf(updateRequest.gender)

        val memberPhone = MemberPhone(
            number=updateRequest.number,
            isNumberVerified = updateRequest.isVerified,
            countryCode = updateRequest.countryCode
        )
        phones.add(memberPhone)

        address = updateRequest.address
    }

    fun addPhone(phone: MemberPhone) {
        phones.add(phone)
    }
}
