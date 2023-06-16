package com.example.breel.ui.fragment.account.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.databinding.ItemRprofileProjectexperienceBinding

class UserProjectExperienceAdapter(private val listUserProjectExperience: List<UserProjectExperience>) : RecyclerView.Adapter<UserProjectExperienceAdapter.ViewHolder>() {

    companion object {
        const val TAG = "UserProjectExperienceAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRprofileProjectexperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listUserProjectExperience.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = listUserProjectExperience[position].title
        val thumbnailUrl = listUserProjectExperience[position].thumbnailUrl
        val description = listUserProjectExperience[position].description

        holder.binding.tvTitle.text = title
        holder.binding.tvDescription.text = description
    }

    class ViewHolder(var binding: ItemRprofileProjectexperienceBinding) : RecyclerView.ViewHolder(binding.root)
}