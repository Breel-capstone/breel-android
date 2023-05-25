package com.example.breel.ui.activity.testing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.api.project.ProjectMentorshipRequest
import com.example.breel.data.api.project.Proposal
import com.example.breel.data.repository.project.ProjectRepository
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val projectRepository: ProjectRepository
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            projectRepository.requestProjectMentorship(1, 5, "ingin bla bla").collect {
                Log.d(this.toString(), "$it")
            }
        }
    }
}