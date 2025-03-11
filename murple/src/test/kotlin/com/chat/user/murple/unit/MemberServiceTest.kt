package com.chat.user.murple.unit

import com.chat.user.murple.domain.Member
import com.chat.user.murple.dto.member.InCreateMember
import com.chat.user.murple.dto.member.InUpdateMember
import com.chat.user.murple.enums.Gender
import com.chat.user.murple.repository.MemberAddressRepository
import com.chat.user.murple.repository.MemberPhoneRepository
import com.chat.user.murple.repository.MemberRepository
import com.chat.user.murple.service.MemberService
import io.mockk.Runs
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(MockKExtension::class)
@SpringBootTest
class MemberServiceTest {

    private lateinit var memberService: MemberService
    private val memberRepository: MemberRepository = mockk<MemberRepository>()
    private val memberPhoneRepository: MemberPhoneRepository = mockk<MemberPhoneRepository>()
    private val memberAddressRepository: MemberAddressRepository = mockk<MemberAddressRepository>()

    @BeforeEach
    fun setUp() {
        memberService = MemberService(memberRepository, memberPhoneRepository, memberAddressRepository)
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
                null, null, null, null,
                null, null)
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

    @Test
    fun `id로 멤버를 삭제하면 해당 멤버 아이디로 조회시 deleteById 호출 확인`() {
        // Given
        val memberId = 1L

        // Mocking: deleteById()가 호출될 수 있도록 설정
        every { memberRepository.deleteById(memberId) } just Runs

        // When
        memberService.deleteMember(memberId)

        // Then
        verify { memberRepository.deleteById(memberId) }  // 메서드가 호출되었는지 검증
    }

    @Test
    fun `멤버 정보 수정 시 정상적으로 반영됨`() {
        // Given
        val memberId = 1L
        val originalMember = Member(id = memberId, name = "John Doe", email = "john@example.com")
        val updatedMember = Member(id = memberId, name = "Jane Doe", email = "jane@example.com")

        // Mocking: findById()와 save() 동작 설정
        every { memberRepository.findMemberById(memberId) } returns originalMember
        every { memberRepository.save(any()) } answers { updatedMember }  // 업데이트된 객체 반환

        // When
        val result = memberService.updateMember(InUpdateMember(
            memberId, "Jane Doe", 20,"jane@example.com",
            "MALE", "010-1234-1234", true,
            "KR", null,null, null
        ))

        // Then
        assertNotNull(result)
        assertEquals("Jane Doe", result?.name)
        assertEquals("jane@example.com", result?.email)
        assertEquals(Gender.MALE, result?.gender)
    }
}
