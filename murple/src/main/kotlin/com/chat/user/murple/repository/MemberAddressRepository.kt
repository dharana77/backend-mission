package com.chat.user.murple.repository

import com.chat.user.murple.domain.MemberAddress
import org.springframework.data.jpa.repository.JpaRepository

interface MemberAddressRepository: JpaRepository<MemberAddress, Long> {
}
