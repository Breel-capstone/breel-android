package com.example.breel.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

object UserUtil {

    suspend fun getUserBearerToken(): String = flow {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        try {
            val tokenResult: GetTokenResult? = currentUser?.getIdToken(true)?.await()
            val bearerToken = tokenResult?.token
            bearerToken?.let {
                emit(it)
            }

            if (bearerToken == null) {
                throw Exception("Failed to retrieve user bearer token")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "getUserBearerToken: $e")
        }
    }.first()
}