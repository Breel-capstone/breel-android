package com.example.breel.ui.fragment.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breel.R
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.mentor.MyMentor
import com.example.breel.databinding.ItemMentorBinding
import com.google.android.material.chip.Chip

class MentorAdapter constructor(private val context: Context) :
    PagingDataAdapter<Mentor, MentorAdapter.ViewHolder>(MENTOR_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mentor = getItem(position)
        mentor?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemMentorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mentor: Mentor) {
            binding.tvName.text = mentor.fullName
            binding.tvSalary.text = mentor.price.toString()
            for (skill in mentor.skills) {
                val skillView = LayoutInflater.from(binding.root.context)
                    .inflate(R.layout.item_profile_chip_skill, null) as Chip
                

                skillView.text = skill
                binding.chipGroup.addView(skillView)
            }
            Glide.with(binding.root.context)
                .load(mentor.profileUrl)
                .into(binding.ivProfilePicture)
        }
    }

    companion object {
        const val TAG = "MentorAdapter"

        object MENTOR_COMPARATOR : DiffUtil.ItemCallback<Mentor>() {
            override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
                return oldItem == newItem
            }
        }
    }
}
