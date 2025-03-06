package com.chat.user.murple.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class MemberPhone (
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    val id: Long? = null,
    val number: String? = null,
    val countryCode: String? = null
)
