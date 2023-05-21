package com.example.breel.data.repository.user

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.user.detail.User
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.data.api.user.detail.UserSkill
import com.example.breel.data.api.user.profile.ProfileResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface UserRepositorySource {

    fun login(email: String, password: String): Flow<Resource<AuthResult>>

    fun register(email: String, password: String): Flow<Resource<AuthResult>>

    fun registerDetail(
        user: User,
        userExperiences: List<UserExperience>,
        userSkills: List<UserSkill>,
        userProjectExperiences: List<UserProjectExperience>
    ): Flow<Resource<BackendResponseNoData>>

    fun getProfile(): Flow<Resource<BackendResponse<ProfileResponse>>>
    fun getUserBearerToken(): Flow<String>

    fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>>
}