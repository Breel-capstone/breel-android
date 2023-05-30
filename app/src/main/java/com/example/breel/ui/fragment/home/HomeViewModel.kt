package com.example.breel.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.breel.data.api.project.Project
import com.example.breel.data.repository.project.ProjectRepository
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val projectRepository: ProjectRepository
) : ViewModel() {
    val projectPagingLiveData: MutableLiveData<PagingData<Project>> by lazy {
        MutableLiveData<PagingData<Project>>()
    }

    fun getProject() {
//        return projectRepository.getProjectsPaging().asLiveData()
        viewModelScope.launch {
            projectRepository.getProjectsPaging().collectLatest {
                projectPagingLiveData.postValue(it)
            }
        }
    }
}