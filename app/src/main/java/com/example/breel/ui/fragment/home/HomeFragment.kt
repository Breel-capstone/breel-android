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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.getUserProfile()
        setUpRecyclerView()
        observeViewModel()
    }


    private fun setUpRecyclerView() {
        projectAdapter = ProjectAdapter()
        mentorProjectAdapter = ProjectAdapter()
        setClientProject()
        setMentorProject()
        setMentor()


//        val adapter3 = MentorAdapter(lstMentor)
//        rv_mentor.adapter = adapter3
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
    }

    private fun observeViewModel() {
        viewModel.getProjects()
        viewModel.getMentorProjects()
        viewModel.projectPagingLiveData.observe(viewLifecycleOwner) {
            handleProjectResult(it)
        }
        viewModel.mentorProjectPagingLiveData.observe(viewLifecycleOwner) {
            handleMentorProjectResult(it)
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
}