package com.example.breel.ui.fragment.notification

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.model.notification.DummyNotification
import com.example.breel.databinding.ItemNotificationBinding

class NotificationAdapter(private val listNotification: List<DummyNotification>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    companion object {
        const val TAG = "NotificationAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNotification.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = listNotification[position].title
        val description = listNotification[position].description

        holder.binding.tvTitle.text = title
        holder.binding.tvDescription.text = description

        holder.itemView.setOnClickListener {
            Log.i(TAG, "HALO")
        }

    }

    class ViewHolder(var binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root)
}
