package com.example.breel.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

object UserUtil {

    suspend fun getUserBearerToken(): String = flow {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        val tokenResult: GetTokenResult? = currentUser?.getIdToken(true)?.await()
        val bearerToken = tokenResult?.token
        Log.d("User Util", "getUserBearerToken: $bearerToken")
        bearerToken?.let {
            emit(it)
            return@flow
        }

        emit("")
        Log.e("User Util", "cannot get bearer token")
    }.first()
}