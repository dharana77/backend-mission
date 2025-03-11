package com.chat.user.murple.unit

import com.chat.user.murple.domain.Member
import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import jakarta.validation.Validator
import kotlin.test.Test

class MemberTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `정상적인 멤버 객체 생성`() {
        val member = Member(name = "홍길동", email = "test@example.com")

        val violations = validator.validate(member)

        assertEquals(0, violations.size) // 유효성 검증 통과
    }

    @Test
    fun `이름이 비어있으면 예외 발생`() {
        val member = Member(name = "", email = "test@example.com")

        val violations = validator.validate(member)

        assertEquals(1, violations.size)
        assertEquals("이름은 최소 1글자에서 1024글자 사이여야 합니다.", violations.first().message)
    }

    @Test
    fun `이메일 형식이 잘못되었을 경우 예외 발생`() {
        val member = Member(name = "홍길동", email = "wrong-email-format")

        val violations = validator.validate(member)

        assertEquals(1, violations.size)
        assertEquals("올바르지 않은 이메일 형식입니다.", violations.first().message)
    }
}
