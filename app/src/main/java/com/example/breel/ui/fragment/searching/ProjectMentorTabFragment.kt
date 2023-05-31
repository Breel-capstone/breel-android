package com.example.breel.ui.fragment.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.databinding.FragmentProjectMentorTabBinding
import com.example.breel.ui.fragment.home.ProjectAdapter

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

        val adapter = ProjectAdapter()
        rv.adapter = adapter
    }
}