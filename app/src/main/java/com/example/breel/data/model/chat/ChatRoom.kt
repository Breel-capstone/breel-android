package com.example.breel.data.model.chat

import com.google.firebase.Timestamp

data class ChatRoom(
    val participants: List<Participant> = listOf(),
    val messages: List<Message> = listOf(),
)

data class Participant(
    val uid: String? = null,
    val name: String? = null,
    val profileUrl: String? = null
)

data class Message(
    val sender: String? = null,
    val text: String? = null,
    val timestamp: Timestamp = Timestamp.now()
)