package com.example.breel.data.repository.user

import android.content.Intent
import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface UserRepositorySource {

    fun login(email: String, password: String): Flow<Resource<AuthResult>>

    fun register(email: String, password: String): Flow<Resource<AuthResult>>

    fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>>
}