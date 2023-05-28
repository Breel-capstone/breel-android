package com.example.breel.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserProfile()

        var lstProject = listOf<DummyProject>(
            DummyProject(
                "Title 1",
                getString(R.string.lorem_2sen),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            ),
            DummyProject(
                "Title 2",
                getString(R.string.lorem_2sen),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            ),
            DummyProject(
                "Title 3",
                getString(R.string.lorem_2sen),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            )
        )
        var lstMentor = listOf<DummyMentor>(
            DummyMentor("Lorem Ipsum", "1.000.000"),
            DummyMentor("Lorem Ipsum", "2.000.000"),
            DummyMentor("Lorem Ipsum", "3.000.000"),
            DummyMentor("Lorem Ipsum", "4.000.000"),

            )

        // First RV
        val rv_client_project: RecyclerView = binding.rvClientProject
        rv_client_project.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ProjectAdapter(lstProject)
        rv_client_project.adapter = adapter

        // Second RV
        val rv_mentor_project: RecyclerView = binding.rvMentorProject
        rv_mentor_project.layoutManager = LinearLayoutManager(requireActivity())

        val adapter2 = ProjectAdapter(lstProject)
        rv_mentor_project.adapter = adapter2

        // Third RV
        val rv_mentor = binding.rvMentor
        rv_mentor.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        val adapter3 = MentorAdapter(lstMentor)
        rv_mentor.adapter = adapter3

    }
}