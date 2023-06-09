package com.example.breel.ui.activity.testing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.model.chat.Message
import com.example.breel.data.repository.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    fun test(successCallBack: (messages: List<Message>) -> Unit) {
        viewModelScope.launch {
            chatRepository.createChatRoom("wRe24YpU8iMF1hXbdImVZ8ubG1U2").collect {
                Log.d(TAG, "test: $it")
            }
//            chatRepository.getChatList().collect {
//                Log.d(TAG, "test: $it")
//                if (it is Resource.Success) {
//                    val reference = it.data?.chat_rooms?.get(0)?.reference
//                    reference?.let {
//                        chatRepository.sendMessage("hello", reference).collect { message ->
//                            Log.d(TAG, "addMessage: $message")
//                        }
//                        chatRepository.addMessageSnapshotListener(reference, successCallBack)
//                    }
//                }
//            }
        }
    }

    companion object {
        const val TAG = "TestViewModel"
    }
}