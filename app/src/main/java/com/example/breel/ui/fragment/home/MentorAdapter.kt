package com.example.breel.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.databinding.ItemMentorBinding

class MentorAdapter(private val listMentor: List<Mentor>) : RecyclerView.Adapter<MentorAdapter.ViewHolder>(){

    companion object {
        const val TAG = "MentorAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMentor.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = listMentor[position].fullName
        val price = listMentor[position].price

        holder.binding.tvName.text = name
        holder.binding.tvSalary.text = price.toString()
    }

    class ViewHolder(var binding: ItemMentorBinding) : RecyclerView.ViewHolder(binding.root)
}