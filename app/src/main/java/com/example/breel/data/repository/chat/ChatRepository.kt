package com.example.breel.data.repository.chat

import com.example.breel.utils.UserUtil
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val fireStoreDb: FirebaseFirestore,
    private val userUtil: UserUtil
) : ChatRepositorySource {

}