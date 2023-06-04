package com.example.breel.data.repository.chat

import android.util.Log
import com.example.breel.data.Resource
import com.example.breel.data.model.chat.ChatList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val fireStoreDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ChatRepositorySource {

    fun getChatList(): Flow<Resource<ChatList>> {
        return flow {
            emit(Resource.Loading())
            val userId = firebaseAuth.uid ?: run {
                emit(Resource.DataError(0, "User ID is null"))
                return@flow
            }

            val chatListRef = fireStoreDb.collection("chat_list").document(userId)

            val snapshot = chatListRef.get().await()
            if (snapshot.exists()) {
                val chatList = snapshot.toObject(ChatList::class.java)
                emit(Resource.Success(data = chatList!!))
                return@flow
            }
            val chatList = ChatList()
            chatListRef.set(chatList).await()
            emit(Resource.Success(data = chatList))
        }.catch {
            Log.e("ChatRepository", "getChatList: $it")
            emit(Resource.DataError(it.hashCode(), it.message))
        }
    }
}