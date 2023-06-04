package com.example.breel.data.repository.chat

import com.example.breel.data.Resource
import com.example.breel.data.model.chat.ChatList
import kotlinx.coroutines.flow.Flow

interface ChatRepositorySource {
    fun getChatList(): Flow<Resource<ChatList>>
}