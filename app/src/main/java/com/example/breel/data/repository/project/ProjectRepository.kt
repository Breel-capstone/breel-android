package com.example.breel.data.repository.project

import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.project.Project
import com.example.breel.data.api.project.Proposal
import com.example.breel.data.repository.processResult
import com.example.breel.utils.UserUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val userUtil: UserUtil,
    private val apiService: ApiService
) : ProjectRepositorySource {
    override fun submitProposal(
        projectId: Int,
        proposal: Proposal
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val result = apiService.submitProposal(projectId, proposal, "Bearer $token").await()
            emitAll(processResult(result))
        }
    }

    override fun getProjects(
        page: Int?,
        limit: Int?,
        disableLimit: Boolean?,
        status: String?,
        isMentored: Boolean?,
        keyword: String?
    ): Flow<Resource<BackendResponse<List<Project>>>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val result = apiService.getProjects(
                page,
                limit,
                disableLimit,
                status,
                isMentored,
                keyword,
                "Bearer $token"
            ).await()
            emitAll(processResult(result))
        }
    }
}