package com.chat.user.murple.service

import com.chat.user.murple.domain.Member
import com.chat.user.murple.domain.MemberAddress
import com.chat.user.murple.domain.MemberPhone
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.repository.MemberAddressRepository
import com.chat.user.murple.repository.MemberPhoneRepository
import com.chat.user.murple.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberPhoneRepository: MemberPhoneRepository,
    private val memberAddressRepository: MemberAddressRepository,
) {
    fun getMember(id: Long): Member? {
        return memberRepository.findMemberById(id);
    }

    fun createMember(createRequest: InCreateMember): Member? {
        val member = Member(
            null,
            name=createRequest.name,
            age=createRequest.age,
            email=createRequest.email,
            gender=createRequest.gender,
            phones =  mutableListOf(),
            addresses = mutableListOf()
        )

        return memberRepository.save(member)
    }

    fun updateMember(updateRequest: InUpdateMember): Member? {
        val member = memberRepository.findMemberById(updateRequest.id)
        member?.update(updateRequest)

        return member
    }

    fun deleteMember(memberId: Long): Long {
        memberRepository.deleteById(memberId)
        return memberId
    }

}
