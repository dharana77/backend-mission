package com.chat.user.murple.controller

import com.chat.user.murple.domain.Member
import com.chat.user.murple.domain.MemberPhone
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.UpdateMemberResponseDto
import com.chat.user.murple.enums.Gender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.dto.member.OutMember
import com.chat.user.murple.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/api/v1/members")
class MemberController (
    private val memberService: MemberService,
){


    @GetMapping("/info/{memberId}")
    fun profile(@PathVariable memberId: Long): OutMember? {
        return memberService.getMember(memberId)?.let { OutMember.fromMember(it) }
    }

    @PostMapping("/create")
    fun createMember(@RequestBody createRequest: InCreateMember): ResponseEntity.BodyBuilder{
        memberService.createMember(createRequest)
        return ResponseEntity.ok()
    }

    @PutMapping("/update")
    fun updateMember(@RequestBody updateRequest: InUpdateMember): ResponseEntity.BodyBuilder{
        memberService.updateMember(updateRequest)
        return ResponseEntity.ok()
    }

    @DeleteMapping("/delete")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity.BodyBuilder {
        memberService.deleteMember(memberId)
        return ResponseEntity.ok()
    }
}
