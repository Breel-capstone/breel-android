package com.example.breel.ui.activity.testing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.repository.project.ProjectRepository
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val projectRepository: ProjectRepository
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            projectRepository.respondProposal(1, 1, "Accepted", 1).collect {
                Log.d(this.toString(), "$it")
            }
        }
    }
}