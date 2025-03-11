package com.chat.user.murple.dto.member

import com.chat.user.murple.domain.Member
import com.chat.user.murple.domain.MemberPhone
import com.chat.user.murple.enums.Gender

data class OutMember (
    val id: Long,
    val name: String,
    val email: String?,
    val gender: Gender?,
    val phones: List<String?>,
    val address: List<String?>
){
    companion object {
        fun fromMember(member: Member): OutMember {
            return OutMember(
                id = member.id!!,
                name = member.name,
                email = member.email,
                gender = member.gender,
                phones = member.phones.map { it.number },
                address = member.addresses.map{ it.address},
            )
        }
    }
}
