package com.example.breel.ui.fragment.project.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.breel.databinding.FragmentProjectDetailBinding

class ProjectDetail : Fragment() {
    private var _binding: FragmentProjectDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateProposal.setOnClickListener {
            val destination = ProjectDetailDirections.actionProjectDetailFragmentToProposalFormFragment()
            findNavController().navigate(destination)
        }
    }

}