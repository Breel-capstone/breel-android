package com.example.breel.data.repository.mentor

import androidx.paging.PagingData
import com.example.breel.data.api.mentor.Mentor
import kotlinx.coroutines.flow.Flow

interface MentorRepositorySource {
    fun getMentors(
    ): Flow<PagingData<Mentor>>
}