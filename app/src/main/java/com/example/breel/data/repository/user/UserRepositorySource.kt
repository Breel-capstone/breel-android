package com.example.breel.data.repository.user

import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserRepositorySource {

    suspend fun login(): Flow<Resource<LoginResponse>>
}