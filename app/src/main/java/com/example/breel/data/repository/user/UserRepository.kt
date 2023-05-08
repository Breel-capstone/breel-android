package com.example.breel.data.repository.user

import android.content.Intent
import android.provider.Settings.Global.getString
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.awaitResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : UserRepositorySource {

  override val currentUser: FirebaseUser?
    get() = firebaseAuth.currentUser

  override suspend fun signInWithGoogle(email: String, password: String): Resource<FirebaseUser> {
    return try {
      val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
      Resource.Success(result.user!!)
    } catch (e: Exception) {
      Resource.DataError(errorCode = 0)
    }
  }
}