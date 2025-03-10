package com.chat.user.murple.service

import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun getMember(id: Long): Member? {
        return memberRepository.findMemberById(id);
    }

    fun createMember(createRequest: InCreateMember): Member? {
        memberRepository
        val member = Member(
            null,
            createRequest.name,
            createRequest.age,
            createRequest.email,
            createRequest.gender,
            createRequest.phoneNumber,
            false,
            "KR",
            createRequest.address)

        return memberRepository.save(member)
    }

    fun updateMember(updateRequest: InUpdateMember): Member? {
        val member = memberRepository.findMemberById(updateRequest.id)

        member?.name = updateRequest.name
        member?.email = updateRequest.email

        return member
    }

    fun deleteMember(memberId: Long): Long {
        memberRepository.deleteById(memberId)
        return memberId
    }

}
