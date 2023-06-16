package com.example.breel.ui.fragment.mentorship.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.data.api.project.Project
import com.example.breel.data.model.temp.ProjectTempData
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpAddButton()
    }

    private fun setUpRecyclerView() {
        val lst = getDummyData()

        val rv = binding.rvProjects
        rv.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MentorshipProjectAdapter(lst)
        rv.adapter = adapter
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorship Proyek")
        mainActionBar.setBackButton()
    }

    private fun setUpAddButton() {
        binding.btnAddProject.setOnClickListener {
            val destination = MentorshipProjectFragmentDirections.actionMentorshipProjectFragmentToCreateMentorshipProjectFragment()
            findNavController().navigate(destination)
        }
    }

    private fun getDummyData() : List<ProjectTempData> {
        val lst = listOf(
            ProjectTempData(0, "Pengembangan Web untuk Aplikasi Toko Online", "Mencari"),
            ProjectTempData(1, "Model untuk Klasifikasi Gambar Sampah", "Mencari"),
        )
        return lst
    }
}