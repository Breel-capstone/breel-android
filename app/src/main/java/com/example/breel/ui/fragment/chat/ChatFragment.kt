package com.example.breel.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    companion object {
        const val TAG = "ChatFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lst = listOf(
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen)
        )

        val rv: RecyclerView = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = MessageAdapter(lst)
        rv.adapter = adapter
    }
}