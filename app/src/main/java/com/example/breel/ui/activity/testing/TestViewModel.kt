package com.example.breel.ui.activity.testing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.api.project.Proposal
import com.example.breel.data.repository.project.ProjectRepository
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val userRepository: UserRepository, private val projectRepository: ProjectRepository): ViewModel(){
    fun test(){
        viewModelScope.launch {
            val proposal = Proposal(30000, 1, "aldsjfakjl")
            projectRepository.submitProposal(1, proposal).collect {
                Log.d(this.toString(), "$it")
            }
        }
    }
}