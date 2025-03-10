package com.chat.user.murple.dto.member

import com.chat.user.murple.domain.Member
import com.chat.user.murple.enums.Gender

data class OutMember (
    val id: Long,
    val name: String,
    val email: String?,
    val gender: Gender?,
    val number: String?,
    val isVerified: Boolean?,
    val countryCode: String?,
    val address: String?
){
    companion object {
        fun fromMember(member: Member): OutMember {
            return OutMember(
                id = member.id!!,
                name = member.name,
                email = member.email,
                gender = member.gender,
                number = member.number,
                isVerified = member.isNumberVerified,
                countryCode = member.countryCode,
                address = member.address
            )
        }
    }
}
