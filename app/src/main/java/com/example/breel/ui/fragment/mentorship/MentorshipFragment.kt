package com.example.breel.ui.fragment.mentorship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.breel.R
import com.example.breel.databinding.FragmentMentorshipBinding
import com.example.breel.databinding.FragmentSearchingBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections

class MentorshipFragment : Fragment() {

    private lateinit var binding: FragmentMentorshipBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorshipBinding.inflate(inflater, container, false)
        setUpActionBar()
        setUpClickListener()
        return binding.root
    }

    private fun setUpClickListener() {
        binding.btnProjectMentor.setOnClickListener {
            val destination = MentorshipFragmentDirections.actionMentorshipFragmentToMentorshipProjectFragment()
            findNavController().navigate(destination)
        }
        binding.btnDailyMentor.setOnClickListener {
            val destination = MentorshipFragmentDirections.actionMentorshipFragmentToDailyMentorshipFragment()
            findNavController().navigate(destination)
        }
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentor")
        mainActionBar.setBackButton()
    }
}