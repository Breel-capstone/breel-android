package com.example.breel.ui.fragment.mentorship.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentDailyMentorshipBinding
import com.example.breel.databinding.FragmentMentorshipProjectBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.home.project.ProjectAdapter

class MentorshipProjectFragment : Fragment() {

    private lateinit var binding: FragmentMentorshipProjectBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorshipProjectBinding.inflate(inflater, container, false)
        setUpActionBar()
        return inflater.inflate(R.layout.fragment_mentorship_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorship Harianmu")
        mainActionBar.setBackButton()
    }
}