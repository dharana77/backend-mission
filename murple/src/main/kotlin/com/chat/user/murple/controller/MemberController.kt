package com.chat.user.murple.controller

import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.dto.member.OutMember
import com.chat.user.murple.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun createMember(@RequestBody createRequest: InCreateMember): ResponseEntity<Member>{
        val member = memberService.createMember(createRequest)
        return ResponseEntity.ok(member)
    }

    @PutMapping("/update")
    fun updateMember(@RequestBody updateRequest: InUpdateMember): ResponseEntity<Member>{
        val member = memberService.updateMember(updateRequest)
        return ResponseEntity.ok(member)
    }

    @DeleteMapping("/delete")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity<Long> {
        memberService.deleteMember(memberId)
        return ResponseEntity.ok(memberId)
    }

    fun view(member: Member) = mapOf("member" to OutMember.fromMember(member))
}
