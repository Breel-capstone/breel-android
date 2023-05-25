package com.example.breel.data.repository.user

import android.util.Log
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.user.detail.RegisterDetailRequest
import com.example.breel.data.api.user.detail.User
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.data.api.user.detail.UserSkill
import com.example.breel.data.api.user.profile.Profile
import com.example.breel.data.repository.processResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService
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

    override fun registerDetail(
        user: User,
        userExperiences: List<UserExperience>,
        userSkills: List<UserSkill>,
        userProjectExperiences: List<UserProjectExperience>
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            emit(Resource.Loading())
            val registerDetailRequest =
                RegisterDetailRequest(user, userExperiences, userSkills, userProjectExperiences)
            val token = getUserBearerToken().first()
            val result = apiService.registerDetail("Bearer $token", registerDetailRequest).await()
            val statusCode = result.meta.statusCode
            val successStatusCodeRange = 200..399
            if (statusCode in successStatusCodeRange) {
                emit(Resource.Success(result))
            } else {
                emit(Resource.DataError(errorCode = statusCode))
            }
        }
    }

    override fun getProfile(): Flow<Resource<BackendResponse<Profile>>> {
        return flow {
            emit(Resource.Loading())
            val token = getUserBearerToken().first()
            val result = apiService.getProfile("Bearer $token").await()
            emitAll(processResult(result))
        }
    }

    override fun getProfile(userId: String): Flow<Resource<BackendResponse<Profile>>> {
        return flow {
            emit(Resource.Loading())
            val token = getUserBearerToken().first()
            val result = apiService.getProfile(userId, "Bearer $token").await()
            emitAll(processResult(result))
        }
    }

    override fun getUserBearerToken(): Flow<String> = flow {
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
//            throw Exception("Failed to retrieve user bearer token", e)
        }
    }

    override fun getUserMentors(
        page: Int?,
        limit: Int?,
        disableLimit: Boolean?
    ): Flow<Resource<BackendResponse<List<Mentor>>>> {
        return flow {
            emit(Resource.Loading())
            val token = getUserBearerToken().first()
            val result =
                apiService.getUserMentors(page, limit, disableLimit, "Bearer $token").await()
            emitAll(processResult(result))
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