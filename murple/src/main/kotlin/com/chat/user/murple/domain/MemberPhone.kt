package com.chat.user.murple.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "member_phone")
@Entity
data class MemberPhone (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null,

    @Column(nullable = false, length = 1024)
    var number: String? = null,

    var type: String? = null,

    var isNumberVerified: Boolean? = false,

    var countryCode: String? = null,
): BaseEntity() {

    fun validateAndSetCountryCode() {
        if (countryCode.isNullOrBlank()) {
            countryCode = extractCountryCode(number)
        }

        // ISO 3166-1 alpha-2 표준 체크 (대문자, 2자리)
        require(countryCode == null || countryCode!!.matches(Regex("^[A-Z]{2}$"))) {
            "Invalid country code: $countryCode"
        }
    }

    private fun extractCountryCode(phoneNumber: String?): String? {
        if (phoneNumber.isNullOrBlank()) return null

        return when {
            phoneNumber.startsWith("+82") -> "KR"
            phoneNumber.startsWith("+1") -> "US"
            phoneNumber.startsWith("+81") -> "JP"
            phoneNumber.startsWith("+86") -> "CN"
            else -> null
        }
    }
}

