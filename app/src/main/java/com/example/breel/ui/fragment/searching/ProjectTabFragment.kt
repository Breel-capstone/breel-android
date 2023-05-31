package com.example.breel.ui.fragment.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentProjectTabBinding
import com.example.breel.ui.fragment.home.ProjectAdapter

class ProjectTabFragment : Fragment() {

    private lateinit var binding: FragmentProjectTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProjectTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.rvProject
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ProjectAdapter()
        rv.adapter = adapter
    }
}