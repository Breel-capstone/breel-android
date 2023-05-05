package com.example.breel.data.api

import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}