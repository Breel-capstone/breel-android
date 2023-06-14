package com.example.breel.ui.fragment.mentorship.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentDailyMentorshipBinding
import com.example.breel.databinding.FragmentMentorshipProjectBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.home.project.ProjectAdapter
import com.example.breel.ui.fragment.mentorship.adapter.MentorshipProjectAdapter

class MentorshipProjectFragment : Fragment() {

    private lateinit var binding: FragmentMentorshipProjectBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorshipProjectBinding.inflate(inflater, container, false)
        setUpActionBar()
        return inflater.inflate(R.layout.fragment_mentorship_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val lst = listOf("Item 1", "Item 2", "Item 3")
        val rv = binding.rvProjects
        val adapter = MentorshipProjectAdapter(lst)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorship Proyek")
        mainActionBar.setBackButton()
    }
}