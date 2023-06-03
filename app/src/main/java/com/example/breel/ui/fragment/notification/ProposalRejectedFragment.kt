package com.example.breel.ui.fragment.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breel.R
import com.example.breel.databinding.FragmentProposalAcceptedBinding
import com.example.breel.ui.component.MainActionBar

class ProposalRejectedFragment : Fragment() {

    private lateinit var binding: FragmentProposalAcceptedBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProposalAcceptedBinding.inflate(inflater, container, false)
        setUpActionBar()
        return binding.root
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Proposal Freelance")
        mainActionBar.setBackButton()
    }
}