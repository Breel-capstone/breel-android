package com.example.breel.ui.fragment.project.proposal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breel.databinding.FragmentSubmitProposalBinding

class SubmitProposalFragment : Fragment() {
    private var _binding: FragmentSubmitProposalBinding? = null
    private val binding:FragmentSubmitProposalBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubmitProposalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}