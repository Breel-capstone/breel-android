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

        val rv_project: RecyclerView = binding.rvClientProject
        rv_project.layoutManager = LinearLayoutManager(requireActivity())

        var lst = listOf<DummyProject>(
            DummyProject(
                "Title 1",
                R.string.lorem_2sen.toString(),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            ),
            DummyProject(
                "Title 2",
                R.string.lorem_2sen.toString(),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            ),
            DummyProject(
                "Title 3",
                R.string.lorem_2sen.toString(),
                "1.000.000",
                "1 Month",
                false,
                listOf("Skill 1", "Skill 2")
            )
        )

        val adapter = ProjectAdapter(lst)
        rv_project.adapter = adapter

    }
}