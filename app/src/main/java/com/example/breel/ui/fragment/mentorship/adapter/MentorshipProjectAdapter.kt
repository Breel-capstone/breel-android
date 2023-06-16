package com.example.breel.ui.fragment.mentorship.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemSimpleProjectBinding

class MentorshipProjectAdapter (private val listProject: List<String>) : RecyclerView.Adapter<MentorshipProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "halo from on create!")
        val binding = ItemSimpleProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "$listProject.size")
        return listProject.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.i(TAG, "halo from on bind!")
//        holder.binding.tvTitle.text = "Pengembangan Web untuk Landing Page Perusahaan"
//        holder.binding.status.text = "Mencari"
    }

    class ViewHolder(var binding: ItemSimpleProjectBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    companion object {
        const val TAG = "MentorshipProjectAdapter"
    }

}