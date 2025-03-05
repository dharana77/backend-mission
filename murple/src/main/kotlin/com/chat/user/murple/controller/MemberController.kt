package com.chat.user.murple.controller

import com.chat.user.murple.domain.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController {

    @GetMapping("/all")
    fun getAllUsers(): List<Member> {
        return listOf(
            Member(1, "유저1"),
            Member(2, "유저2")
        )
    }



}
