package com.example.breel.data.repository.chat

import android.util.Log
import com.example.breel.data.Resource
import com.example.breel.data.api.ApiService
import com.example.breel.data.model.chat.ChatList
import com.example.breel.data.model.chat.ChatRoom
import com.example.breel.data.model.chat.ChatRoomData
import com.example.breel.data.model.chat.Message
import com.example.breel.data.model.chat.Participant
import com.example.breel.utils.UserUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.await
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val fireStoreDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val apiService: ApiService,
    private val userUtil: UserUtil
) : ChatRepositorySource {

    override fun getChatList(uid: String?): Flow<Resource<ChatList>> {
        return flow {
            emit(Resource.Loading())
            val userId = uid ?: firebaseAuth.uid ?: run {
                emit(Resource.DataError(0, "User ID is null"))
                return@flow
            }

            val chatListRef = fireStoreDb.collection("chat_list").document(userId)

            val snapshot = chatListRef.get().await()
            if (snapshot.exists()) {
                Log.d("ChatRespository", "getChatList: ${snapshot.data}")
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

    override fun createChatRoom(uid: String): Flow<Resource<DocumentReference>> {
        return flow {
            emit(Resource.Loading())
            val token = userUtil.getUserBearerToken()

            val profile = apiService.getProfile(userId = uid, token = "Bearer $token").await()
            val myProfile = apiService.getProfile("Bearer $token").await()

            val profileData = profile.data ?: throw IllegalStateException("Profile data is null")
            val myProfileData =
                myProfile.data ?: throw IllegalStateException("My profile data is null")

            val receiver =
                Participant(profileData.uid, profileData.fullName, profileData.profileUrl)
            val sender =
                Participant(myProfileData.uid, myProfileData.fullName, myProfileData.profileUrl)
            val participants = listOf(receiver, sender)

            val chatRoomRef = fireStoreDb.collection("chat_room")
            val chatRoom = ChatRoom(participants)
            val chatRoomRefResult = chatRoomRef.add(chatRoom).await()

            getChatList(profileData.uid).drop(1).first()
            getChatList(myProfileData.uid).drop(1).first()

            val chatListRefSender = fireStoreDb.collection("chat_list").document(myProfileData.uid)
            val updateFieldSender = hashMapOf<String, Any>(
                "chat_rooms" to FieldValue.arrayUnion(
                    ChatRoomData(
                        receiver,
                        chatRoomRefResult,
                        "Halo terimakasih sudah chat"
                    )
                )
            )
            chatListRefSender.update(updateFieldSender).await()

            val chatListRefReceiver = fireStoreDb.collection("chat_list").document(profileData.uid)

            val updateFieldReceiver = hashMapOf<String, Any>(
                "chat_rooms" to FieldValue.arrayUnion(
                    ChatRoomData(
                        sender,
                        chatRoomRefResult,
                        "Halo terimakasih sudah chat"
                    )
                )
            )
            chatListRefReceiver.update(updateFieldReceiver).await()

            emit(Resource.Success(chatRoomRefResult))
        }.catch {
            Log.e("ChatRepository", "createChatRoom: $it")
            emit(Resource.DataError(it.hashCode(), it.message))
        }
    }

    override fun addMessageSnapshotListener(
        chatRoomReference: DocumentReference,
        successCallBack: (messages: List<Message>) -> Unit
    ) {
        chatRoomReference.addSnapshotListener { snapshots, e ->
            if (e != null) {
                Log.w("ChatRepository", "listen:error", e)
                return@addSnapshotListener
            }

            snapshots?.let {
                val chatRoom = it.toObject(ChatRoom::class.java)
                chatRoom?.let {
                    successCallBack(chatRoom.messages)
                    return@addSnapshotListener
                }
            }
        }
    }

    fun sendMessage(
        text: String,
        chatRoomReference: DocumentReference
    ): Flow<Resource<Message>> {
        return flow {
            emit(Resource.Loading())
            val uid = firebaseAuth.uid
            val sendMessage = Message(uid, text)
            val updateField = hashMapOf<String, Any>(
                "messages" to FieldValue.arrayUnion(
                    sendMessage
                )
            )
            chatRoomReference.update(updateField).await()
            emit(Resource.Success(sendMessage))
        }.catch {
            Log.e("ChatRepository", "createChatRoom: $it")
            emit(Resource.DataError(it.hashCode(), it.message))
        }
    }
}