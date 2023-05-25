package com.example.breel.data.api

import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.project.Proposal
import com.example.breel.data.api.user.detail.RegisterDetailRequest
import com.example.breel.data.api.user.profile.Profile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //    User
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("user/register-detail")
    fun registerDetail(
        @Header("Authorization") token: String,
        @Body registerDetailRequest: RegisterDetailRequest
    ): Call<BackendResponseNoData>

    @GET("user/profile")
    fun getProfile(
        @Header("Authorization") token: String,
    ): Call<BackendResponse<Profile>>

    @GET("user/profile/{userId}")
    fun getProfile(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): Call<BackendResponse<Profile>>

    @GET("user/mentor")
    fun getUserMentors(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("disableLimit") disableLimit: Boolean?,
        @Header("Authorization") token: String
    ): Call<BackendResponse<List<Mentor>>>

    //    Project
    @POST("project/{projectId}/proposal")
    fun submitProposal(
        @Path("projectId") projectId: Int,
        @Body body: Proposal,
        @Header("Authorization") token: String
    ): Call<BackendResponseNoData>

}