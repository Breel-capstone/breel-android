package com.example.breel.data.model.chat

import com.google.firebase.firestore.DocumentReference

data class ChatList(
    val chat_rooms: List<ChatRoomData> = listOf()
)
data class ChatRoomData(
    val receiver: Participant? = null,
    val reference: DocumentReference? = null
)
