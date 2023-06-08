package com.example.breel.ui.fragment.home.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.data.api.project.Project
import com.example.breel.databinding.ItemProjectBinding
import com.google.android.material.chip.Chip

class ProjectAdapter : PagingDataAdapter<Project, ProjectAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "ProjectAdapter"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = getItem(position)
        project?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(project: Project) {
            binding.tvTitle.text = project.title
            binding.tvDescription.text = project.description
            binding.tvSalary.text = project.budget.toString()
            binding.tvDuration.text = project.durationMonth.toString()
            for (skill in project.skills) {
                val skillView = LayoutInflater.from(binding.root.context)
                    .inflate(R.layout.item_profile_chip_skill, null) as Chip
                skillView.text = skill
                binding.chipGroup.addView(skillView)
            }
        }
    }
}