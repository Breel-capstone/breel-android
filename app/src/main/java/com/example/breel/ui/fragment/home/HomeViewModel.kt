package com.example.breel.ui.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    fun getUserProfile() {
        viewModelScope.launch {
            userRepository.getProfile().collect {
                Log.d("HomeViewModel", "$it")
            }
        }
    }
}