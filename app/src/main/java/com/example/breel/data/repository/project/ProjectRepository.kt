package com.example.breel.data.repository.project

import com.example.breel.data.api.ApiService
import com.example.breel.utils.UserUtil
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val userUtil: UserUtil,
    private val apiService: ApiService
) : ProjectRepositorySource {
}