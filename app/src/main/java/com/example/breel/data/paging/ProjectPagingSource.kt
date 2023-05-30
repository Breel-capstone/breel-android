package com.example.breel.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.project.Project
import retrofit2.await
import javax.inject.Inject

class ProjectPagingSource constructor(
    private val apiService: ApiService,
    private val token: String,
    private val limit: Int? = null,
    private val disableLimit: Boolean? = null,
    private val status: String? = null,
    private val isMentored: Boolean? = null,
    private val keyword: String? = null
) : PagingSource<Int, Project>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Project> {
        return try {
            val nextPage = params.key ?: 1

            val result = apiService.getProjects(
                page = nextPage,
                limit = limit,
                disableLimit = disableLimit,
                status = status,
                isMentored = isMentored,
                keyword = keyword,
                token = "Bearer $token"
            ).await()
            Log.d("projectPagingSource", "load: $result")

            val projects = result.data!!

            LoadResult.Page(
                data = projects,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (projects.isNotEmpty()) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Project>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}