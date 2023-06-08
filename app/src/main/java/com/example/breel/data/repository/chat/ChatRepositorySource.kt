package com.example.breel.data.repository.chat

import com.example.breel.data.Resource
import com.example.breel.data.model.chat.ChatList
import com.example.breel.data.model.chat.Message
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow

interface ChatRepositorySource {
    fun getChatList(uid: String? = null): Flow<Resource<ChatList>>

    fun createChatRoom(uid: String): Flow<Resource<DocumentReference>>

    fun addMessageSnapshotListener(
        chatRoomReference: DocumentReference,
        successCallBack: (messages: List<Message>) -> Unit
    )
}