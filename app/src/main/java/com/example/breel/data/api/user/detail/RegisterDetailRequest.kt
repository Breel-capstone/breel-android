package com.example.breel.data.api.user.detail

data class User(
    val fullName: String,
    val title: String,
    val description: String,
    val profileUrl: String
)

data class UserExperience(
    val companyName: String,
    val location: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val description: String
)

data class UserSkill(
    val skillName: String
)

data class UserProjectExperience(
    val title: String,
    val thumbnailUrl: String,
    val description: String
)

data class RegisterDetailRequest(
    val user: User,
    val userExperiences: List<UserExperience>,
    val userSkills: List<UserSkill>,
    val userProjectExperiences: List<UserProjectExperience>
)
