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
    val gender: Gender = Gender.MALE
)
