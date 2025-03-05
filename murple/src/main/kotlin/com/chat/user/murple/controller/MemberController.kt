package com.chat.user.murple.controller

import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.UpdateMemberResponseDto
import com.chat.user.murple.enums.Gender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.chat.user.murple.dto.UpdateMemberRequestDto
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/api/v1/members")
class MemberController {

    @GetMapping("/all")
    fun getAllMembers(): List<Member> {
        return listOf(
            Member(1, "유저1", Gender.MALE),
            Member(2, "유저2", Gender.FEMALE)
        )
    }

    @PostMapping("/create")
    fun createMember(@RequestBody member: Member): Member {
        return member
    }

    @PutMapping("/update")
    fun updateMember(@RequestBody updateRequest: UpdateMemberRequestDto): UpdateMemberResponseDto {
        return UpdateMemberResponseDto().of(updateRequest)
    }

    @DeleteMapping("/delete")
    fun deleteMember(@RequestBody member: Member): Member {
        return member
    }
}
