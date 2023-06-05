package com.example.breel.ui.fragment.chat

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.model.chat.Message
import com.example.breel.databinding.ItemMessageBinding


class MessageAdapter(private val uid: String) :
    ListAdapter<Message, MessageAdapter.ViewHolder>(MessageDiffCallback()) {

    inner class ViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvMessage.text = message.text

            if (message.sender == uid) {
                binding.messageContainer.gravity = Gravity.RIGHT
            }
            else {
                binding.messageContainer.gravity = Gravity.LEFT

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

    // Create a custom DiffUtil.ItemCallback for Message
    class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem === newItem // Modify this based on your item uniqueness logic
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.text == newItem.text
        }
    }
}
