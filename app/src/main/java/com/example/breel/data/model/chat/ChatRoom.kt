package com.example.breel.data.model.chat

import com.google.firebase.Timestamp

data class ChatRoom(
    val participants: List<Participant> = listOf(),
    val messages: List<Message> = listOf(),
)

data class Participant(
    val uid: String,
    val name: String,
    val profileUrl: String
)

data class Message(
    val sender: String,
    val text: String,
    val timestamp: Timestamp
)