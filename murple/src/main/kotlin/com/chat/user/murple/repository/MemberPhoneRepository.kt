package com.chat.user.murple.repository

import com.chat.user.murple.domain.MemberPhone
import org.springframework.data.jpa.repository.JpaRepository

interface MemberPhoneRepository : JpaRepository<MemberPhone, Long> {
}
