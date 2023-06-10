package com.example.breel.ui.fragment.mentorship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemPendingMenteeBinding

class PendingMenteeAdapter(private val listMentee: List<String>) : RecyclerView.Adapter<PendingMenteeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPendingMenteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMentee.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(var binding: ItemPendingMenteeBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
