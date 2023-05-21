package com.example.breel.data.api

import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.data.api.user.detail.RegisterDetailRequest
import com.example.breel.data.api.user.profile.ProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("user/register-detail")
    fun registerDetail(
        @Header("Authorization") token: String,
        @Body registerDetailRequest: RegisterDetailRequest
    ): Call<BackendResponseNoData>

    @POST("user/profile")
    fun getProfile(
        @Header("Authorization") token: String,
    ): Call<BackendResponse<ProfileResponse>>


}