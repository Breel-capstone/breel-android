package com.example.breel.data.repository.project

import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.project.Proposal
import kotlinx.coroutines.flow.Flow

interface ProjectRepositorySource {
    fun submitProposal(projectId: Int, proposal: Proposal): Flow<Resource<BackendResponseNoData>>
}