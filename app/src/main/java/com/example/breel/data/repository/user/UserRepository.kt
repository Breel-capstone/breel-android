package com.example.breel.data.repository.user

import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.data.local.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import retrofit2.awaitResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val apiService: ApiService,
  private val userPreferences: UserPreferences
) : UserRepositorySource {

  override suspend fun login(): Flow<Resource<LoginResponse>> {
    return flow {
      emit(Resource.Loading())
      val loginRequest = LoginRequest(
        "adiva123@gmail.com",
        "123adiva"
      )
      val response = apiService.login(loginRequest)
      response.awaitResponse().run {
        if (isSuccessful) emit(Resource.Success(body()!!))
        else emit(Resource.DataError(code()))
      }
    }
  }

  override fun getDummyString(): String {
    return userPreferences.dummyString
  }

  override fun saveToken(token: String) {
    runBlocking {
      userPreferences.saveToken(token)
    }
  }
}