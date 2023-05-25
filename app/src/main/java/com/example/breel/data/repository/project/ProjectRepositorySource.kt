package com.example.breel.data.repository.project

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.project.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepositorySource {
    fun submitProposal(
        projectId: Int,
        price: Int,
        durationMonth: Int,
        coverLetter: String
    ): Flow<Resource<BackendResponseNoData>>

    fun getProjects(
        page: Int? = null,
        limit: Int? = null,
        disableLimit: Boolean? = null,
        status: String? = null,
        isMentored: Boolean? = null,
        keyword: String? = null
    ): Flow<Resource<BackendResponse<List<Project>>>>

    fun requestProjectMentorship(
        projectId: Int,
        budgetPercentage: Int,
        restriction: String
    ): Flow<Resource<BackendResponseNoData>>

    fun respondProposal(
        projectId: Int,
        proposalId: Int,
        status: String,
        applicantId: Int
    ): Flow<Resource<BackendResponseNoData>>
}