package com.example.breel.data.repository.mentor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.mentor.MyMentor
import com.example.breel.data.paging.MentorPagingSource
import com.example.breel.utils.UserUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MentorRepository @Inject constructor(
    private val userUtil: UserUtil,
    private val apiService: ApiService
) : MentorRepositorySource {

    override fun getMentors(
    ): Flow<PagingData<Mentor>> = flow {
        val token = userUtil.getUserBearerToken()
        emit(token)
    }.flatMapConcat { token ->
        val pagingConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )

        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MentorPagingSource(
                    apiService,
                    token,
                )
            }
        ).flow
    }
}