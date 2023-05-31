package com.example.breel.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.breel.data.api.ApiService
import com.example.breel.data.api.mentor.Mentor
import retrofit2.await

class MentorPagingSource constructor(
    private val apiService: ApiService,
    private val token: String,
    private val keyword: String? = null
): PagingSource<Int, Mentor>()  {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Mentor> {
        return try {
            val nextPage = params.key ?: 1

            val result = apiService.getMentors(
                page = nextPage,
                token = "Bearer $token",
                disableLimit = null,
                keyword = keyword,
                limit = null
            ).await()
            Log.d("mentorPagingSource", "load: $result")

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

    override fun getRefreshKey(state: PagingState<Int, Mentor>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}