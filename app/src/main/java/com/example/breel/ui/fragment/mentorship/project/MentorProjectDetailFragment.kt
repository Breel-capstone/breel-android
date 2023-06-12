package com.example.breel.ui.fragment.mentorship.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentDailyMentorshipBinding
import com.example.breel.databinding.FragmentMentorProjectDetailBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.mentorship.adapter.ApplicantsAdapter

class MentorProjectDetailFragment : Fragment() {

    private lateinit var binding: FragmentMentorProjectDetailBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorProjectDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lst = listOf("Item 1", "Item 2", "Item 3")

        val rv = binding.rvPendaftar
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ApplicantsAdapter(lst)
        rv.adapter = adapter
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Detail Proyek")
        mainActionBar.setBackButton()
    }
}