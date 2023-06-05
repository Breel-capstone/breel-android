package com.example.breel.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.Resource
import com.example.breel.data.model.chat.Message
import com.example.breel.databinding.FragmentChatRoomBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.component.StatusSnackBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatRoomFragment : Fragment() {

    private lateinit var statusSnackBar: StatusSnackBar
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var binding: FragmentChatRoomBinding
    private lateinit var mainActionBar: MainActionBar
    private lateinit var chatRoomReference: DocumentReference

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var fireStoreDb: FirebaseFirestore

    companion object {
        const val TAG = "ChatFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatRoomBinding.inflate(inflater, container, false)
        setUpActionBar()
        chatRoomReference = fireStoreDb.document("chat_room/amLXJKISnEjg2eD9YnIz")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusSnackBar = StatusSnackBar(view)
        setMessageAdapter()
        setOnClickListener()
        observeViewModel()
    }


    private fun observeViewModel() {

        viewModel.snapShotListener(chatRoomReference) { messages ->
            messageAdapter.submitList(messages)
        }

        viewModel.messageResult.observe(viewLifecycleOwner) {
            handleMessageResult(it)
        }
    }

    private fun handleMessageResult(status: Resource<Message>) {
        if (status is Resource.DataError) {
            status.errorMessage?.let { statusSnackBar.showError(it) }
            return
        }
    }

    private fun setMessageAdapter() {
        val rv: RecyclerView = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(requireActivity())
        messageAdapter = MessageAdapter(firebaseAuth.uid ?: "")
        rv.adapter = messageAdapter
    }

    private fun setOnClickListener() {
        binding.ivSend.setOnClickListener {
            viewModel.sendMessage(chatRoomReference, binding.edtType.text.toString())
            binding.edtType.text = null
        }
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        // todo: change the title to the recipient's name
        mainActionBar.setTitle("Chat")
        mainActionBar.setBackButton()
    }

}