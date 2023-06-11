package com.example.breel.ui.fragment.mentorship.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemApplyingBinding

class ApplicantsAdapter (private val listApplicant: List<String>) : RecyclerView.Adapter<ApplicantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemApplyingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listApplicant.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(var binding: ItemApplyingBinding) : RecyclerView.ViewHolder(binding.root){

    }
}