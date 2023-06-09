package com.example.breel.ui.fragment.chat.room

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
    private val viewModel: ChatRoomViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var binding: FragmentChatRoomBinding
    private lateinit var mainActionBar: MainActionBar
    private lateinit var chatRoomReference: DocumentReference

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var fireStoreDb: FirebaseFirestore
    private var path: String? = null

    companion object {
        const val TAG = "ChatFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatRoomBinding.inflate(inflater, container, false)
        mainActionBar = MainActionBar(this)
        setUpActionBar()
        getPath()
        chatRoomReference = fireStoreDb.document(path ?: "")
        return binding.root
    }

    private fun getPath() {
        arguments?.let {
            path = ChatRoomFragmentArgs.fromBundle(it).id
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusSnackBar = StatusSnackBar(view)
        setMessageAdapter()
        setOnClickListener()
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        setUpActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

        }
        binding.ivPlus.setOnClickListener {
            Toast.makeText(requireContext(), "You clicked the button!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpActionBar() {
        mainActionBar.setTitle("Chat")
        mainActionBar.setBackButton()
    }

}