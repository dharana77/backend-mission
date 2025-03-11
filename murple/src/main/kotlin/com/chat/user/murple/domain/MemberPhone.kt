package com.chat.user.murple.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "member_phone")
@Entity
data class MemberPhone (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null,

    @Column(nullable = false)
    var number: String? = null,

    var type: String? = null,

    var isNumberVerified: Boolean? = false,

    var countryCode: String? = null,
): BaseEntity() {
}

