package com.example.breel.ui.fragment.notification

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.databinding.ItemNotificationBinding


class NotificationAdapter(
    private val onItemClick: (id: String) -> Unit
) :
    ListAdapter<NotificationData, NotificationAdapter.ViewHolder>(NotificationDiffCallback()) {

    companion object {
        const val TAG = "NotificationAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notificationData = getItem(position)

        holder.binding.tvTitle.text = notificationData.title
        holder.binding.tvDescription.text = notificationData.description

        Glide.with(holder.binding.root.context)
            .load(notificationData.profileUrl)
            .into(holder.binding.ivProfile)

        holder.itemView.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: ")
            onItemClick(notificationData.id)
        }
    }

    class ViewHolder(var binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root)

    private class NotificationDiffCallback : DiffUtil.ItemCallback<NotificationData>() {

        override fun areItemsTheSame(
            oldItem: NotificationData,
            newItem: NotificationData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NotificationData,
            newItem: NotificationData
        ): Boolean {
            return oldItem == newItem
        }
    }
}
