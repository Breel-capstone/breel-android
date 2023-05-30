package com.example.breel.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.project.Project
import com.example.breel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var mentorProjectAdapter: ProjectAdapter
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeViewModel()
    }


    private fun setUpRecyclerView() {
        projectAdapter = ProjectAdapter()
        mentorProjectAdapter = ProjectAdapter()
        mentorAdapter = MentorAdapter(requireContext())
        setClientProject()
        setMentorProject()
        setMentor()
    }


    private fun setClientProject() {
        binding.rvClientProject.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvClientProject.adapter = projectAdapter
    }

    private fun setMentorProject() {
        binding.rvMentorProject.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMentorProject.adapter = mentorProjectAdapter
    }

    private fun setMentor() {
        binding.rvMentor.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMentor.adapter = mentorAdapter
    }

    private fun observeViewModel() {
        viewModel.getProjects()
        viewModel.getMentorProjects()
        viewModel.getMentors()
        viewModel.projectPagingLiveData.observe(viewLifecycleOwner) {
            handleProjectResult(it)
        }
        viewModel.mentorProjectPagingLiveData.observe(viewLifecycleOwner) {
            handleMentorProjectResult(it)
        }
        viewModel.mentorPagingLiveData.observe(viewLifecycleOwner) {
            handleMentorResult(it)
        }

    }


    private fun handleProjectResult(pagingData: PagingData<Project>) {
        lifecycleScope.launch {
            projectAdapter.submitData(pagingData)
        }
    }

    private fun handleMentorProjectResult(pagingData: PagingData<Project>) {
        lifecycleScope.launch {
            mentorProjectAdapter.submitData(pagingData)
        }
    }

    private fun handleMentorResult(pagingData: PagingData<Mentor>) {
        lifecycleScope.launch {
            mentorAdapter.submitData(pagingData)
        }
    }
}