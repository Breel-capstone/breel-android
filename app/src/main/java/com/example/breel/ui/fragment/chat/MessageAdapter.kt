package com.example.breel.ui.fragment.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemMessageBinding
import com.example.breel.ui.fragment.notification.NotificationAdapter

class MessageAdapter(private val listMessages: List<String>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    companion object {
        const val TAG = "MessageAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMessages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvMessage.text = listMessages[position]
    }

    class ViewHolder(var binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

}
