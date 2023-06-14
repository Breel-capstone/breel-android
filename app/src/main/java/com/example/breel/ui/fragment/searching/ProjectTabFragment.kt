package com.example.breel.ui.fragment.searching

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
import com.example.breel.data.api.project.Project
import com.example.breel.databinding.FragmentProjectTabBinding
import com.example.breel.ui.fragment.home.HomeViewModel
import com.example.breel.ui.fragment.home.project.ProjectAdapter
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
import com.example.breel.ui.fragment.searching.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProjectTabFragment : Fragment() {

    private lateinit var binding: FragmentProjectTabBinding
//    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProjectTabBinding.inflate(inflater, container, false)
        setUpRecyclerView()
//        observeViewModel()
        return binding.root
    }

    private fun setUpRecyclerView() {
        setProject()
    }

    private fun setProject() {
        val rv: RecyclerView = binding.rvProject
        rv.layoutManager = LinearLayoutManager(requireActivity())
        projectAdapter = ProjectAdapter {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProjectDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = projectAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.rvProject
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ProjectAdapter {
            // todo fix this
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProjectDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = adapter
    }

//    private fun observeViewModel() {
//        viewModel.getProjects()
//        viewModel.projectPagingLiveData.observe(viewLifecycleOwner) {
//            handleProjectResult(it)
//        }
//    }

    private fun handleProjectResult(pagingData: PagingData<Project>) {
        lifecycleScope.launch {
            projectAdapter.submitData(pagingData)
        }
    }
}