package com.example.breel.ui.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.api.project.Project
import com.example.breel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var itemSize: Int = 10
    private lateinit var projectAdapter: ProjectAdapter

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

//        viewModel.getUserProfile()
        setUpRecyclerView()
        observeViewModel()
    }


    private fun setUpRecyclerView() {

        // First RV
        val rv_client_project: RecyclerView = binding.rvClientProject
        rv_client_project.layoutManager = LinearLayoutManager(requireActivity())

        projectAdapter = ProjectAdapter()
        rv_client_project.adapter = projectAdapter


        // Second RV
        val rv_mentor_project: RecyclerView = binding.rvMentorProject
        rv_mentor_project.layoutManager = LinearLayoutManager(requireActivity())

//        val adapter2 = ProjectAdapter(lstProject)
//        rv_mentor_project.adapter = adapter2

        // Third RV
        val rv_mentor = binding.rvMentor
        rv_mentor.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

//        val adapter3 = MentorAdapter(lstMentor)
//        rv_mentor.adapter = adapter3
    }

    private fun observeViewModel() {
        viewModel.getProject()
        viewModel.projectPagingLiveData.observe(viewLifecycleOwner) {
            Log.d("observeViewModle", "$it")
            handleProjectResult(it)
        }
    }

    private fun handleProjectResult(pagingData: PagingData<Project>) {

        Log.d("handleProjectResult", "Test")
        lifecycleScope.launch {
            Log.d("handleProjectResult", "lifecycle")
            projectAdapter.submitData(pagingData)
            val snapshot = projectAdapter.snapshot()
            Log.d("handleProjectResult", "$snapshot")
        }
    }
}