package com.example.breel.data.repository.user

import androidx.lifecycle.LiveData
import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface UserRepositorySource {

    suspend fun login(): Flow<Resource<LoginResponse>>

    fun getDummyString(): String

    fun saveToken(token: String)

    fun getToken(): LiveData<String>
}