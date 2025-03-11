package com.chat.user.murple.dto.member

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class InUpdateMember (
    val id: Long,

    @field:NotBlank(message = "이름은 필수 입력 값입니다.")
    @field:Size(min = 2, max = 30, message = "이름은 2자 이상 30자 이하로 입력해야 합니다.")
    val name: String,

    val age: Int?,

    @field:NotBlank(message = "이메일은 필수 입력 값입니다.")
    @field:Email(message = "유효한 이메일 주소를 입력해야 합니다.")
    val email: String,

    val gender: String,

    val number:String,

    val isVerified: Boolean?,

    val countryCode: String?,

    val phoneType: String?,

    val address: String?
)
