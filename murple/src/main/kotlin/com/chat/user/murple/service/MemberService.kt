package com.chat.user.murple.service

import com.chat.user.murple.repository.MemberRepository
import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.InUpdateMember
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun getMember(userId: Long): Member? {
        return memberRepository.getMemberByUserId(userId);
    }

    fun createMember(createReuqest: InCreateMember): Member? {
        return memberRepository.createMember(
            createReuqest.name,
        )
    }

    fun updateMember(updateRequest: InUpdateMember): Member? {
        return memberRepository.updateMember(
            updateRequest.name,)
    }

    fun deleteMember(memberId: Long): Boolean {
        return memberRepository.deleteMember()
    }

}
