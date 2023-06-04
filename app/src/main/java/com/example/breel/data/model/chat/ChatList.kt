package com.example.breel.data.model.chat

import com.google.firebase.firestore.DocumentReference

data class ChatList(
    val chat_rooms: List<DocumentReference> = listOf()
)
data class ChatRoomReference(
    val receiver: Participant,
    val reference: DocumentReference
)
