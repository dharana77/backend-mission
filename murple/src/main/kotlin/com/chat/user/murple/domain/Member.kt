package com.chat.user.murple.domain

import com.chat.user.murple.enums.Gender
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor

@Entity
@AllArgsConstructor
data class Member (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val age: Int? = null,
    val email: String? = null,
    val gender: Gender? = null,
    val phoneInfo: MemberPhone? = null,
    val address: MemberAddress? = null,
    )
