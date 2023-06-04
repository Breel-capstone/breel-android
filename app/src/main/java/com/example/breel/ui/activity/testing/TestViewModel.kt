package com.example.breel.ui.activity.testing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.repository.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    fun test() {
        Log.d("TestViewModel", "test: ")
        viewModelScope.launch {
            chatRepository.createChatRoom("E2cyCoEW1dcmC0NGG5O21ZAEcX53").collect {
                Log.d("TestViewModel", "test: $it")
            }
        }
    }
}