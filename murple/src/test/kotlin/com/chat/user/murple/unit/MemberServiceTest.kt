package com.chat.user.murple.unit

import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.repository.MemberRepository
import com.chat.user.murple.service.MemberService
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@ExtendWith(MockKExtension::class)
@SpringBootTest
class MemberServiceTest {

    private lateinit var memberService: MemberService
    private val memberRepository: MemberRepository = mockk<MemberRepository>()

    @BeforeEach
    fun setUp() {
        memberService = MemberService(memberRepository)
    }

    @Test
    fun 멤버_생성_테스트() {
        //Given
        val member = Member(id=1L, name="John Doe", email="john@example.com")
        every{ memberRepository.save(any()) } returns member

        // When
        val savedMember = memberService.createMember(
            InCreateMember(
                "John Doe", 17, "john@example.com",
                null, null, null)
        )

        // Then
        assertNotNull(savedMember?.id)
        assertEquals("John Doe", savedMember!!.name)
        assertEquals("john@example.com", savedMember.email)

        verify { memberRepository.save(any()) }
    }

    @Test
    fun `id로 멤버를 조회할 때 정상적으로 반환`() {
        // Given
        val memberId = 1L
        val member = Member(id = memberId, name = "John Doe", email = "john@example.com")

        // Mocking: findMemberById 호출 시 member 반환
        every { memberRepository.findMemberById(memberId) } returns member

        // When
        val foundMember = memberService.getMember(memberId)

        // Then
        assertNotNull(foundMember)
        assertEquals(memberId, foundMember?.id)
        assertEquals("John Doe", foundMember?.name)
    }


}
