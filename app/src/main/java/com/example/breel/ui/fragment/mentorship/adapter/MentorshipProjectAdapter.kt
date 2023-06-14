package com.example.breel.ui.fragment.mentorship.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemSimpleProjectBinding

class MentorshipProjectAdapter (private val listProject: List<String>) : RecyclerView.Adapter<MentorshipProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSimpleProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProject.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(var binding: ItemSimpleProjectBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}