package com.example.breel.data.api.user.profile

data class Profile(
    val id: Int,
    val uid: String,
    val email: String,
    val roleId: Int,
    val fullName: String,
    val title: String,
    val description: String,
    val profileUrl: String,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val createdBy: String,
    val updatedBy: String,
    val deletedBy: String
)
