package com.example.breel.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.project.Project
import com.example.breel.databinding.FragmentHomeBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.home.mentor.MentorAdapter
import com.example.breel.ui.fragment.home.project.ProjectAdapter
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
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
        setUpRecyclerView()
        observeViewModel()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        MainActionBar(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLihatSemua()
    }

    private fun setUpRecyclerView() {
        setClientProject()
        setMentorProject()
        setMentor()
    }


    private fun setClientProject() {
        val rv: RecyclerView = binding.rvClientProject
        rv.layoutManager = LinearLayoutManager(requireActivity())
        projectAdapter = ProjectAdapter {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProjectDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = projectAdapter
    }

    private fun setMentorProject() {
        val rv: RecyclerView = binding.rvMentorProject
        rv.layoutManager = LinearLayoutManager(requireActivity())
        mentorProjectAdapter = ProjectAdapter {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProjectDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = mentorProjectAdapter
    }

    private fun setMentor() {
        val rv: RecyclerView = binding.rvMentor
        rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        mentorAdapter = MentorAdapter {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToMentorDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = mentorAdapter
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

    private fun setUpLihatSemua() {
        // todo navigate to spesific tab in Searching Fragment
        val btnLihatSemua1 = binding.btnLihatSemua1
        btnLihatSemua1.setOnClickListener {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToSearchingFragment()
            findNavController().navigate(destination)
        }

        val btnLihatSemua2 = binding.btnLihatSemua2
        btnLihatSemua2.setOnClickListener {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToSearchingFragment()
            findNavController().navigate(destination)
        }

        val btnLihatSemua3 = binding.btnLihatSemua3
        btnLihatSemua3.setOnClickListener {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToSearchingFragment()
            findNavController().navigate(destination)
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