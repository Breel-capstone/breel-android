package com.example.breel.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.databinding.ItemProjectBinding

class ProjectAdapter(private val listProject: List<DummyProject>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    companion object {
        const val TAG = "ProjectAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProject.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = listProject[position].title
        val description = listProject[position].description
        val salary = listProject[position].salary
        val duration = listProject[position].duration
        val skill = listProject[position].skillList

        holder.binding.tvTitle.text = title
        holder.binding.tvDescription.text = description
        holder.binding.tvSalary.text = salary
        holder.binding.tvDuration.text = duration

        // iteratu thru skill (list).
        // for each, create chip inside flexbox.

    }

    class ViewHolder(var binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root)
}