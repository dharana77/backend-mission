package com.chat.user.murple.repository

import com.chat.user.murple.domain.Member
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Long>{

    fun findMemberById(id: Long): Member?

    fun findTop5ByNameOrderByCreatedAtAsc(name: String): List<Member>
}
