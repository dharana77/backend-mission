package com.chat.user.murple.domain

import com.chat.user.murple.dto.UpdateMemberRequestDto
import com.chat.user.murple.enums.Gender
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor

@Entity
@Table(name = "members")
@AllArgsConstructor
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 1024)
    val name: String,
    val age: Int? = null,
    @Column(length = 1024)
    val email: String? = null,
    val gender: Gender? = null,
    val phoneInfo: MemberPhone? = null,
    @Column(nullable = false, length = 1024)
    val address: String? = null,

) : BaseEntity() {
    fun update(updateRequest: UpdateMemberRequestDto) {
        //TODO
    }
}
