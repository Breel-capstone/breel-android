package com.example.breel.ui.fragment.chat.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.Resource
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.databinding.FragmentChatListBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.component.StatusSnackBar
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
import com.example.breel.ui.fragment.notification.adapter.NotificationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment : Fragment() {

    private lateinit var chatAdapter: NotificationAdapter
    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatListViewModel by viewModels()
    private lateinit var statusSnackBar: StatusSnackBar
    private lateinit var mainActionBar: MainActionBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        mainActionBar = MainActionBar(this)
        setUpActionBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusSnackBar = StatusSnackBar(view)
        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        val rv: RecyclerView = binding.chatList
        rv.layoutManager = LinearLayoutManager(requireActivity())
        chatAdapter = NotificationAdapter {
            Log.d("ChatListFragment", "observeViewModel: $it")
            val destination =
                NavigationFragmentDirections.actionNavigationFragment2ToChatRoomFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = chatAdapter
    }

    private fun observeViewModel() {
        viewModel.getChatList()
        viewModel.chatListResult.observe(viewLifecycleOwner) {
            handleChatListResult(it)
        }
    }

    private fun handleChatListResult(status: Resource<List<NotificationData>>) {
        if (status is Resource.Success) {
            status.data?.let {
                chatAdapter.submitList(it)
            }
        }
        if (status is Resource.DataError) {
            status.errorMessage?.let { statusSnackBar.showError(it) }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        mainActionBar.hideActionBar()
    }

    private fun setUpActionBar() {
        mainActionBar.setTitle("Chat")
    }

}