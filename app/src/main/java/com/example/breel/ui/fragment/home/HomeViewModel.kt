package com.example.breel.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.project.Project
import com.example.breel.data.repository.mentor.MentorRepository
import com.example.breel.data.repository.project.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val mentorRepository: MentorRepository
) : ViewModel() {
    val projectPagingLiveData: MutableLiveData<PagingData<Project>> by lazy {
        MutableLiveData<PagingData<Project>>()
    }

    val mentorProjectPagingLiveData: MutableLiveData<PagingData<Project>> by lazy {
        MutableLiveData<PagingData<Project>>()
    }

    val mentorPagingLiveData: MutableLiveData<PagingData<Mentor>> by lazy {
        MutableLiveData<PagingData<Mentor>>()
    }

    fun getProjects() {
        viewModelScope.launch {
            projectRepository.getProjectsPaging().collectLatest {
                projectPagingLiveData.postValue(it)
            }
        }
    }

    fun getMentorProjects() {
        viewModelScope.launch {
            projectRepository.getProjectsPaging(isMentored = true).collectLatest {
                mentorProjectPagingLiveData.postValue(it)
            }
        }
    }

    fun getMentors() {
        viewModelScope.launch {
            mentorRepository.getMentors().collectLatest {
                mentorPagingLiveData.postValue(it)
            }
        }
    }
}