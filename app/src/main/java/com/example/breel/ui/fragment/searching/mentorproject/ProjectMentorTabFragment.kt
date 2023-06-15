package com.example.breel.ui.fragment.searching.mentorproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.databinding.FragmentProjectMentorTabBinding
import com.example.breel.ui.fragment.home.project.ProjectAdapter
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections

class ProjectMentorTabFragment : Fragment() {

    private lateinit var binding: FragmentProjectMentorTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProjectMentorTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rv = binding.rvProjectmentor
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ProjectAdapter {
            // todo fix this
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProjectDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = adapter
    }
}