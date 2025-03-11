package com.chat.user.murple.dto.chat

data class OutMatchUsers(
    val message: String,
    val matchedUserIds: List<Long?>
)
