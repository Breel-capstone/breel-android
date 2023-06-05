package com.example.breel.ui.fragment.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.model.chat.Message
import com.example.breel.data.repository.chat.ChatRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {

    val messageResult: MutableLiveData<Resource<Message>> by lazy {
        MutableLiveData<Resource<Message>>()
    }

    fun snapShotListener(
        chatRoomReference: DocumentReference,
        successCallBack: (messages: List<Message>) -> Unit
    ) {
        chatRepository.addMessageSnapshotListener(chatRoomReference, successCallBack)
    }

    fun sendMessage(
        chatRoomReference: DocumentReference,
        text: String
    ) {
        viewModelScope.launch {
            chatRepository.sendMessage(text, chatRoomReference).collect {
                messageResult.postValue(it)
            }
        }
    }
}