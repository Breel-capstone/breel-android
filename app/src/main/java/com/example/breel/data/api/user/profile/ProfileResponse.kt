package com.example.breel.data.api.user.profile

import java.time.LocalDateTime

data class ProfileResponse(
    val id: Int,
    val uid: String,
    val email: String,
    val roleId: Int,
    val fullName: String,
    val title: String,
    val description: String,
    val profileUrl: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val createdBy: String,
    val updatedBy: String,
    val deletedBy: String
)
