package com.example.breel.data.model.chat

import com.google.firebase.firestore.DocumentReference

data class ChatList(
    val chatRooms: List<DocumentReference> = listOf()
)