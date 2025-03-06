package com.chat.user.murple.dto.member

import com.chat.user.murple.enums.Gender

data class InCreateMember (
    val name: String,
    val email: String,
    val gender: Gender?,
    val phoneNumber: String?,
    val address: String?
)
