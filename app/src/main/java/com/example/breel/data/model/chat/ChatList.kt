package com.example.breel.data.model.chat

import com.google.firebase.firestore.DocumentReference

data class ChatList(
    val chat_rooms: List<ChatRoomReference> = listOf()
)
data class ChatRoomReference(
    val receiver: Participant? = null,
    val reference: DocumentReference? = null
)
