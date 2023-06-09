package com.example.breel.data.repository.user

import android.util.Log
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.mentor.MyMentor
import com.example.breel.data.api.user.detail.RegisterDetailRequest
import com.example.breel.data.api.user.detail.User
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.data.api.user.detail.UserSkill
import com.example.breel.data.api.user.profile.Profile
import com.example.breel.data.repository.processResult
import com.example.breel.utils.UserUtil
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService,
    private val userUtil: UserUtil
) : UserRepositorySource {

    companion object {
        const val TAG = "UserRepository"
    }

    override fun login(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
            Log.i(TAG, "login success")
        }.catch {
            emit(Resource.DataError(errorCode = 0))
        }
    }

    override fun register(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
            Log.i(TAG, "register success")
        }.catch {
            emit(Resource.DataError(errorCode = 0))
        }
    }

    override fun registerDetail(
        fullName: String,
        title: String,
        description: String,
        userExperiences: List<UserExperience>,
        userSkills: List<UserSkill>,
        userProjectExperiences: List<UserProjectExperience>
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            val user = User(
                fullName,
                title,
                description,
                "https://api.dicebear.com/6.x/open-peeps/svg?clothingColor=17231d&skinColor=fdf2f5&seed=$fullName"
            )
            emit(Resource.Loading())
            val registerDetailRequest =
                RegisterDetailRequest(user, userExperiences, userSkills, userProjectExperiences)
            val token = userUtil.getUserBearerToken()
            val result = apiService.registerDetail("Bearer $token", registerDetailRequest).await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun getProfile(): Flow<Resource<BackendResponse<Profile>>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val result = apiService.getProfile("Bearer $token").await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun checkUserDetailComplete(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            Log.d("UserRepository", "checkUserDetailComplete: $token")
            val result = apiService.getProfile("Bearer $token").await()
            val title = result.data?.title
            if (title == null) {
                emit(Resource.Success(false))
            } else {
                emit(Resource.Success(true))
            }
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun getProfile(userId: String): Flow<Resource<BackendResponse<Profile>>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val result = apiService.getProfile(userId, "Bearer $token").await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun getUserMentors(
        page: Int?,
        limit: Int?,
        disableLimit: Boolean?
    ): Flow<Resource<BackendResponse<List<MyMentor>>>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val result =
                apiService.getUserMentors(page, limit, disableLimit, "Bearer $token").await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
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