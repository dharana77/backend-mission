package com.chat.user.murple.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateMemberRequestDto (
    @field:NotBlank(message = "이름은 필수 입력 값입니다.")
    @field:Size(min = 2, max = 30, message = "이름은 2자 이상 30자 이하로 입력해야 합니다.")
    val name: String,

    @field:NotBlank(message = "이메일은 필수 입력 값입니다.")
    @field:Email(message = "유효한 이메일 주소를 입력해야 합니다.")
    val email: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해야 합니다.")
    val password: String
)
