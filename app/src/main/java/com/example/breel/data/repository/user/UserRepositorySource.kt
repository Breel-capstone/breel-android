package com.example.breel.data.repository.user

import android.content.Intent
import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface UserRepositorySource {

    val currentUser: FirebaseUser?

    suspend fun signInWithGoogle(
        email: String,
        password: String
    ) : Resource<FirebaseUser>
}