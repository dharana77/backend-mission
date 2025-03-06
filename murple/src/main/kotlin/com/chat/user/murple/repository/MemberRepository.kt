package com.chat.user.murple.repository

import com.chat.user.murple.domain.Member
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Long>{

    fun getMemberByUserId(userId: Long): Member?

    fun createMember(name: String): Member?

    fun updateMember(name: String): Member?

    fun deleteMember(): Boolean
}
