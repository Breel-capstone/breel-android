package com.example.breel.ui.fragment.mentorship.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemYourMenteeBinding

class YourMenteeAdapter(private val listMentee: List<String>) : RecyclerView.Adapter<YourMenteeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemYourMenteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMentee.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(var binding: ItemYourMenteeBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
