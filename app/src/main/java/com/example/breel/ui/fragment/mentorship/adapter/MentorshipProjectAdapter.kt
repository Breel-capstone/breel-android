package com.example.breel.ui.fragment.mentorship.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.data.model.temp.ProjectTempData
import com.example.breel.databinding.ItemSimpleProjectBinding
import com.example.breel.ui.fragment.mentorship.project.MentorshipProjectFragmentDirections

class MentorshipProjectAdapter (private val listProject: List<ProjectTempData>) : RecyclerView.Adapter<MentorshipProjectAdapter.ViewHolder>() {

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
        Log.i(TAG, "halo from on bind!")
        holder.binding.tvTitle.text = listProject[position].title
        holder.binding.status.text = listProject[position].status
        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mentorshipProjectFragment_to_mentorProjectDetailFragment)
        )
    }

    class ViewHolder(var binding: ItemSimpleProjectBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    companion object {
        const val TAG = "MentorshipProjectAdapter"
    }

}