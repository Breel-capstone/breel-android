package com.example.breel.ui.fragment.chat.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.data.repository.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) :
    ViewModel() {

    val chatListResult: MutableLiveData<Resource<List<NotificationData>>> by lazy {
        MutableLiveData<Resource<List<NotificationData>>>()
    }

    fun getChatList() {
        viewModelScope.launch {
            chatRepository.getChatList().collect { resource ->
                val transformedResource = when (resource) {
                    is Resource.Success -> {
                        val notificationDataList =
                            resource.data?.chat_rooms?.mapNotNull { chatRoomData ->
                                chatRoomData.receiver?.let { receiver ->
                                    chatRoomData.reference?.let {
                                        NotificationData(
                                            id = it.path,
                                            title = receiver.name ?: "",
                                            description = chatRoomData.lastMessage ?: "",
                                            profileUrl = receiver.profileUrl ?: ""
                                        )
                                    }
                                }
                            }
                        Resource.Success(notificationDataList ?: emptyList())
                    }

                    is Resource.DataError -> Resource.DataError(
                        resource.errorCode ?: -1,
                        resource.errorMessage
                    )

                    is Resource.Loading -> Resource.Loading()
                }

                chatListResult.postValue(transformedResource)
            }
        }
    }
}