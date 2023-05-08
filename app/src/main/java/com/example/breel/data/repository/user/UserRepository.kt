package com.example.breel.data.repository.user

import android.content.Intent
import android.provider.Settings.Global.getString
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.login.LoginRequest
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.data.local.UserPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.awaitResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val firebaseAuth: FirebaseAuth,
  private val userPreferences: UserPreferences
) : UserRepositorySource {

  override fun login(email: String, password: String): Flow<Resource<AuthResult>> {
    return flow {
      emit(Resource.Loading())
      val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
      emit(Resource.Success(result))
    }.catch {
      emit(Resource.DataError(errorCode = 0))
    }
  }

  override fun register(email: String, password: String): Flow<Resource<AuthResult>> {
    return flow {
      emit(Resource.Loading())
      val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
      emit(Resource.Success(result))
    }.catch {
      emit(Resource.DataError(errorCode = 0))
    }
  }

  override fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>> {
    return flow {
      emit(Resource.Loading())
      val result = firebaseAuth.signInWithCredential(credential).await()
      emit(Resource.Success(result))
    }.catch {
      emit(Resource.DataError(errorCode = 0))
    }
  }
}