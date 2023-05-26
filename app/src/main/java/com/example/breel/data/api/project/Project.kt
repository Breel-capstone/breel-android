package com.example.breel.data.api.project

data class Project(
    val id: Int,
    val clientId: Int,
    val title: String,
    val description: String,
    val durationMonth: Int,
    val budget: Int,
    val status: String,
    val skills: String,
    val mentorId: Int,
    val assigneeId: Int,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val createdBy: String,
    val updatedBy: String,
    val deletedBy: String
)
