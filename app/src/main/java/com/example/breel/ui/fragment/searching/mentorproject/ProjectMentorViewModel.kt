package com.example.breel.ui.fragment.searching.mentorproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.breel.data.api.project.Project
import com.example.breel.data.repository.project.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectMentorViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
) : ViewModel() {
    val mentorProjectPagingLiveData: MutableLiveData<PagingData<Project>> by lazy {
        MutableLiveData<PagingData<Project>>()
    }

    fun getMentorProjects() {
        viewModelScope.launch {
            projectRepository.getProjectsPaging(isMentored = true).collectLatest {
                mentorProjectPagingLiveData.postValue(it)
            }
        }
    }
}