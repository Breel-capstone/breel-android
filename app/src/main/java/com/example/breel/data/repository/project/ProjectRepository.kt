package com.example.breel.data.repository.project

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.project.Project
import com.example.breel.data.api.project.ProjectMentorshipRequest
import com.example.breel.data.api.project.proposal.Proposal
import com.example.breel.data.api.project.proposal.RespondProposalRequest
import com.example.breel.data.paging.ProjectPagingSource
import com.example.breel.data.repository.processResult
import com.example.breel.utils.UserUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.await
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val userUtil: UserUtil,
    private val apiService: ApiService
) : ProjectRepositorySource {
    override fun submitProposal(
        projectId: Int,
        price: Int,
        durationMonth: Int,
        coverLetter: String
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            emit(Resource.Loading())

            val proposal = Proposal(price, durationMonth, coverLetter)
            val token = userUtil.getUserBearerToken()
            val result = apiService.submitProposal(projectId, proposal, "Bearer $token").await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun getProjectsPaging(
        page: Int?,
        limit: Int?,
        disableLimit: Boolean?,
        status: String?,
        isMentored: Boolean?,
        keyword: String?
    ): Flow<PagingData<Project>> = flow {
        val token = userUtil.getUserBearerToken()
        emit(token)
    }.flatMapConcat { token ->
        val pagingConfig = PagingConfig(
            pageSize = limit ?: 10,
            enablePlaceholders = false
        )

        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                ProjectPagingSource(
                    apiService,
                    token,
                    limit,
                    disableLimit,
                    status,
                    isMentored,
                    keyword
                )
            }
        ).flow
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
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun requestProjectMentorship(
        projectId: Int,
        budgetPercentage: Int,
        restriction: String
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val requestBody = ProjectMentorshipRequest(budgetPercentage, restriction)
            val result =
                apiService.requestProjectMentorship(projectId, requestBody, "Bearer $token").await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }

    override fun respondProposal(
        projectId: Int,
        proposalId: Int,
        status: String,
        applicantId: Int
    ): Flow<Resource<BackendResponseNoData>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()
            val requestBody = RespondProposalRequest(status, applicantId)
            val result =
                apiService.respondProposal(projectId, proposalId, requestBody, "Bearer $token")
                    .await()
            emitAll(processResult(result))
        }.catch {
            emit(Resource.DataError(errorCode = 0, it.message))
        }
    }
}