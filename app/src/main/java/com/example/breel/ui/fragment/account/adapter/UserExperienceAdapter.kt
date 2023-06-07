package com.example.breel.ui.fragment.account.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.databinding.ItemRprofileExperienceBinding

class UserExperienceAdapter(private val listUserExperience: List<UserExperience>) : RecyclerView.Adapter<UserExperienceAdapter.ViewHolder>() {

    companion object {
        const val TAG = "UserExperienceAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRprofileExperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listUserExperience.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = listUserExperience[position].title
        val startDate = listUserExperience[position].startDate
        val endDate = listUserExperience[position].endDate
        val companyName = listUserExperience[position].companyName
        val location = listUserExperience[position].location
        val description = listUserExperience[position].description

        Log.i(TAG, "${title}, ${startDate}, ${companyName}")

        holder.binding.tvTitle.text = title
        holder.binding.tvDuration.text = "${startDate} - ${endDate}"
        holder.binding.tvCompanyLocation.text = "${companyName}, ${location}"
        holder.binding.tvDescription.text = description
    }

    class ViewHolder(var binding: ItemRprofileExperienceBinding) : RecyclerView.ViewHolder(binding.root)
}