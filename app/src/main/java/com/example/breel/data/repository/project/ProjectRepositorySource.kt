package com.example.breel.data.repository.project

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponse
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.project.Project
import com.example.breel.data.api.project.Proposal
import kotlinx.coroutines.flow.Flow

interface ProjectRepositorySource {
    fun submitProposal(projectId: Int, proposal: Proposal): Flow<Resource<BackendResponseNoData>>
    fun getProjects(
        page: Int? = null,
        limit: Int? = null,
        disableLimit: Boolean? = null,
        status: String? = null,
        isMentored: Boolean? = null,
        keyword: String? = null
    ): Flow<Resource<BackendResponse<List<Project>>>>
}