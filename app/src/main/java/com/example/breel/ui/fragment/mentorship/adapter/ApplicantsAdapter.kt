package com.example.breel.ui.fragment.mentorship.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.data.model.temp.ApplicantTempData
import com.example.breel.databinding.ItemApplyingBinding

class ApplicantsAdapter (private val listApplicant: List<ApplicantTempData>) : RecyclerView.Adapter<ApplicantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemApplyingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listApplicant.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = listApplicant[position].name
        holder.binding.skill.text = listApplicant[position].skill
        holder.binding.btnLihatProposal.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mentorProjectDetailFragment_to_proposalDetailFragment)
        )
    }

    inner class ViewHolder(var binding: ItemApplyingBinding) : RecyclerView.ViewHolder(binding.root){

    }
}